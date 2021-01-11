
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeGetter {

    private String currentApiKey = "cae37f32b37e4c3a9375f05f796efd79";

    private String[] apiKeys = {
            "beade7dbaeb243d4beb34dff964ccd2c",
            "47849262aafc4f9e913a93b1c17550ee",
            "4a3aec7e117042bc855d072c2b7e37b1",
            "cae37f32b37e4c3a9375f05f796efd79",
            "80a77f2c371643a584df540c4185d2f3",
            "42852e6e405b451e8df79f42514d78ab",
            "e4cefaa2e1444732997af8bd9f4728af",
            "5b9d6410756941408198e435862ed407",
            "15ed5962188d48b8a844de37b82d2421",
            "fa4bb03f978c472093b016d2ed0e9f34",
            "cd24a451453140f8ab68dac3c7382bdb",
            "a7e72d0e97ff42b39fac4c20cc1a51bc"};

    public RecipeGetter() {
        checkAPIkey();
    }


    /**
     * Checks the quota left for all API-keys and changes the currentApiKey variable to the one with the least quota used.
     */
    public void checkAPIkey() {
        HttpResponse<JsonNode> response;

        double quota = 5000;
        String newApiKey = currentApiKey;

        for(int i = 0; i<apiKeys.length; i++) {
            try {
                response = Unirest.get("https://api.spoonacular.com/food/converse/suggest")
                        .queryString("apiKey", apiKeys[i])
                        .queryString("query", "tell")
                        .queryString("number", 1)
                        .asJson();

                Headers headers = response.getHeaders();
                headers.get("X-API-Quota-Used");
                List quotaList = headers.get("X-API-Quota-Used");

                if(quotaList != null) {
                    String quotaString = (String)quotaList.get(0);
                    double temp = Double.parseDouble(quotaString);
                    if(temp < quota) {
                     quota = temp;
                        newApiKey = apiKeys[i];
                    }
                }
            } catch(UnirestException e) {

            }
        }

        currentApiKey = newApiKey;
    }

    /**
     * Get recipes från Spoonacular based on both requested grape and ingredients in season right now.
     * @param wineSelection the grape to match
     * @param seasonIngredients list of ingredients in season right now
     * @return an ArrayList with Recipe objects
     */
    public ArrayList<Recipe> getByWineAndSeason(String wineSelection, ArrayList<String> seasonIngredients) {

        checkAPIkey();

        StringBuilder ingredients = new StringBuilder();
        for(int i = 0; i<seasonIngredients.size(); i++) {
            ingredients.append(seasonIngredients.get(i) + ",+");
        }
        ingredients.deleteCharAt(ingredients.lastIndexOf(","));
        ingredients.deleteCharAt(ingredients.lastIndexOf("+"));

        JSONArray dishes = getByWine(wineSelection);

        HttpResponse<JsonNode> response;
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        Random rand = new Random();

        for(int i = 0; i<dishes.length(); i++) {
            for(int j = 0; j<5; j++) {
                try {

                    response = Unirest.get("https://api.spoonacular.com/recipes/complexSearch")
                            .queryString("apiKey", currentApiKey)
                            .queryString("query", dishes.get(i))
                            .queryString("includeIngredients", seasonIngredients.get(rand.nextInt(seasonIngredients.size())))
                            .queryString("instructionsRequired", true)
                            .queryString("number", 5)
                            .asJson();

                    JsonNode json = response.getBody();
                    JSONObject jsonObject = json.getObject();
                    JSONArray array = jsonObject.getJSONArray("results");
                    for(int k = 0; k<array.length(); k++) {
                        JSONObject recipe = array.getJSONObject(k);
                        int id = recipe.getInt("id");
                        String imageURL = recipe.getString("image");
                        String title = recipe.getString("title");

                        Recipe newRecipe = new Recipe(title, imageURL, id);
                        recipes.add(newRecipe);
                    }

                } catch(UnirestException e) {

                }
            }

        }

        //Remove duplicates
        ArrayList<Recipe> recipesNoDupes = new ArrayList<Recipe>();
        for(int i = 0; i<recipes.size(); i++) {

            boolean dupe = false;

            for(int j = 0; j<recipesNoDupes.size(); j++) {
                if(recipesNoDupes.get(j).getId() == recipes.get(i).getId()) {
                    dupe = true;
                }
            }

            if(dupe == false) {
                recipesNoDupes.add(recipes.get(i));
            }
        }
        return recipesNoDupes;
    }

    /**
     * Gets a recipe from Spoonacular based on ID and returns it as a Recipe-object.
     * @param id id of recipe to be fetched
     * @return Recipe-object with the id specified
     */
    public Recipe getById(int id) {
        checkAPIkey();
        //Om något går fel så returneras ett recipe-objekt med titeln "No recipe found"
        Recipe recipeResult = new Recipe("No recipe found");
        HttpResponse<JsonNode> response;
        String[] ingredients;
        String title;
        String description;
        String imageURL;
        ArrayList<String> instructions;
        String requestURL = "https://api.spoonacular.com/recipes/" + id + "/information";

        try {

            response = Unirest.get(requestURL)
                    .queryString("apiKey", "cae37f32b37e4c3a9375f05f796efd79")
                    .asJson();

            JsonNode json = response.getBody();
            JSONObject jsonObject = json.getObject();

            //Hämta alla ingredienser och lägga dem i en String-array
            JSONArray ingredientResults = jsonObject.getJSONArray("extendedIngredients");
            ingredients = new String[ingredientResults.length()];

            for(int i = 0; i<ingredientResults.length(); i++) {
                JSONObject ingredientName = ingredientResults.getJSONObject(i);
                int amount = ingredientName.getInt("amount");
                String newIngredient = amount + " " + ingredientName.getString("unit") + " " + ingredientName.getString("name");

                ingredients[i] = newIngredient;
            }

            title = jsonObject.getString("title");

            description = removeHTMLTags(jsonObject.getString("summary"));

            imageURL = jsonObject.getString("image");

            instructions = getInstructionsByID(id);

            recipeResult = new Recipe(title, imageURL, id, description, ingredients, instructions);

        } catch (UnirestException e) {

        }
                
        return recipeResult;
    }

    /**
     * Removes all HTML-tags from a String, and returns the edited String.
     * @param string String to have HTML-tags removed
     * @return String without HTML-tags
     */
    private String removeHTMLTags(String string) {
        StringBuilder description = new StringBuilder(string);

        int x = 0;
        while(x == 0) {
            description.replace(description.lastIndexOf("<"), description.lastIndexOf(">")+1, "");
            if(description.lastIndexOf(">") == -1) {
                x = -1;
            }
        }

        return description.toString();
    }

    /**
     * Gets instructions for the specified recipe and puts them in order in an ArrayList.
     * @param id id of recipe selected
     * @return ArrayList of instructions in order
     */
    private ArrayList<String> getInstructionsByID(int id) {

        checkAPIkey();
        HttpResponse<JsonNode> response;
        String requestURL = "https://api.spoonacular.com/recipes/" + id + "/analyzedInstructions";

        ArrayList<String> instructionResults = new ArrayList<String>();

        try {
            response = Unirest.get(requestURL)
                    .queryString("apiKey", currentApiKey)
                    .queryString("stepBreakdown", false)
                    .asJson();

            JsonNode json = response.getBody();
            JSONArray instructions = json.getArray();


            for (int i = 0; i<instructions.length(); i++) {
                JSONObject stepSection = instructions.getJSONObject(i);

                instructionResults.add(stepSection.getString("name"));

                JSONArray steps = stepSection.getJSONArray("steps");

                for(int j = 0; j<steps.length(); j++) {
                    JSONObject stepStep = steps.getJSONObject(j);
                    instructionResults.add(stepStep.getInt("number") + ". " + stepStep.getString("step"));

                }
            }

        } catch(UnirestException e) {

        }

        return instructionResults;
    }

    /**
     * Retrived food types based on grape from the Spoonacular API.
     * Only used by getByWineAndSeason-method.
     * @param wineSelection the choosen grape
     * @return JSONArray of food types
     */
    private JSONArray getByWine(String wineSelection) {

        checkAPIkey();
        HttpResponse<JsonNode> wineResponse;

        try {
            wineResponse = Unirest.get("https://api.spoonacular.com/food/wine/dishes")
                    .queryString("wine", wineSelection)
                    .queryString("apiKey", currentApiKey)
                    .asJson();



            JsonNode json = wineResponse.getBody();
            JSONObject jsonObject = json.getObject();
            JSONArray dishes = jsonObject.getJSONArray("pairings");

            return dishes;
        } catch(UnirestException e) {
            System.err.println(e);
        }
        return null;
    }
}
