
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class RecipeGetter {

    public RecipeGetter() {

    }

    public ArrayList<Recipe> getByWineAndSeasonTwo(String wineSelection, ArrayList<String> seasonIngredients) {

        StringBuilder ingredients = new StringBuilder();
        for(int i = 0; i<seasonIngredients.size(); i++) {
            ingredients.append(seasonIngredients.get(i) + ",+");
        }
        ingredients.deleteCharAt(ingredients.lastIndexOf(","));
        ingredients.deleteCharAt(ingredients.lastIndexOf("+"));

        JSONArray dishes = getByWine(wineSelection);

        HttpResponse<JsonNode> response;
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        try {
            response = Unirest.get("https://api.spoonacular.com/recipes/findByIngredients")
                    .queryString("ingredients", ingredients)
                    .queryString("ignorePantry", false)
                    .queryString("number", 100)
                    .queryString("apiKey", "cae37f32b37e4c3a9375f05f796efd79")
                    .asJson();

            JsonNode json = response.getBody();
            JSONArray array = json.getArray();

            for(int i = 0; i<array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String title = jsonObject.getString("title");
                String imageURL = jsonObject.getString("image");
                int id = jsonObject.getInt("id");

                Recipe recipe = new Recipe(title, imageURL, id);

                recipes.add(recipe);

            }
        } catch(UnirestException e) {

        }

        ArrayList<Recipe> recipeResults = new ArrayList<Recipe>();

        for(int i = 0; i<recipes.size(); i++) {
            boolean dishTrue = false;

            for(int j = 0; j<dishes.length(); j++) {
                String dish = dishes.getString(j);

                if(recipes.get(i).getTitle().contains(dish) == true) {
                    dishTrue = true;
                }
            }


            if(dishTrue == true) {
                recipeResults.add(recipes.get(i));
            }
        }

        return recipeResults;
    }

    /**
     * Hämtar recept baserat på både vindruvan som är vald och säsongsingredienserna från Spoonacular.
     * @param wineSelection valda druvan
     * @param seasonIngredients lista av ingredienser som är i säsong
     * @return ett RecipeBook-objekt som innehåller alla sökresultat
     */
    public ArrayList<Recipe> getByWineAndSeason(String wineSelection, ArrayList<String> seasonIngredients) {

        StringBuilder ingredients = new StringBuilder();
        for(int i = 0; i<seasonIngredients.size(); i++) {
            ingredients.append(seasonIngredients.get(i) + ",+");
        }
        ingredients.deleteCharAt(ingredients.lastIndexOf(","));
        ingredients.deleteCharAt(ingredients.lastIndexOf("+"));

        System.out.println(ingredients);

        JSONArray dishes = getByWine(wineSelection);


        if(dishes == null) {
            System.out.println("nothing");
            return null; //TODO proper error handling
        }

        HttpResponse<JsonNode> response;
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        Random rand = new Random();
        //TODO start fetching recipes based on the different dishes in JSONArray
        for(int i = 0; i<dishes.length(); i++) {
            for(int j = 0; j<5; j++) {
                try {
                    System.out.println(i);
                    response = Unirest.get("https://api.spoonacular.com/recipes/complexSearch")
                            .queryString("apiKey", "cae37f32b37e4c3a9375f05f796efd79")
                            .queryString("query", dishes.get(i))
                            .queryString("includeIngredients", seasonIngredients.get(rand.nextInt(seasonIngredients.size())))
                            .queryString("instructionsRequired", true)
                            .queryString("number", 5)
                            .asJson();

                    System.out.println(response.getBody());
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

    public Recipe getById(int id) {
        //Om något går fel så returneras ett recipe-objekt med titeln "No recipe found"
        Recipe recipeResult = new Recipe("No recipe found");
        HttpResponse<JsonNode> response;
        String[] ingredients;
        String title;
        String description;
        String imageURL;
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
                ingredients[i] = ingredientName.getString("name");
            }

            title = jsonObject.getString("title");
            description = jsonObject.getString("summary");
            imageURL = jsonObject.getString("image");

            recipeResult = new Recipe(title, imageURL, id, description, ingredients);

        } catch (UnirestException e) {

        }
                
        return recipeResult;
    }

    /**
     * Hämtar alla mat-typer baserat på vindruvan som angivits, genom en request till Spoonacular-API:t.
     * Kallas enbart på av getByWineAndSeason-metoden.
     * @param wineSelection valda vindruvan
     * @return JSONArray av mat-typer
     */

    private JSONArray getByWine(String wineSelection) {

        HttpResponse<JsonNode> wineResponse;

        try {
            wineResponse = Unirest.get("https://api.spoonacular.com/food/wine/dishes")
                    .queryString("wine", wineSelection)
                    .queryString("apiKey", "cae37f32b37e4c3a9375f05f796efd79")
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
