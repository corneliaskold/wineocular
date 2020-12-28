
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeGetter {

    public RecipeGetter() {

    }

    public RecipeBook getByWineAndSeason(String wineSelection, ArrayList<String> seasonIngredients) {

        StringBuilder ingredients = new StringBuilder();
        for(int i = 0; i<seasonIngredients.size(); i++) {
            ingredients.append(seasonIngredients.get(i) + ",");
        }
        ingredients.deleteCharAt(seasonIngredients.lastIndexOf(","));

        JSONArray dishes = getByWine(wineSelection);

        if(dishes == null) {
            return null; //TODO proper error handling
        }

        HttpResponse<JsonNode> response;
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        //TODO start fetching recipes based on the different dishes in JSONArray
        for(int i = 0; i<dishes.length(); i++) {
            try {
                response = Unirest.get("https://api.spoonacular.com/recipes/complexSearch")
                        .queryString("apiKey", "cae37f32b37e4c3a9375f05f796efd79")
                        .queryString("query", dishes.get(i))
                        .queryString("includeIngredients", ingredients)
                        .queryString("number", 3)
                        .asJson();

                JsonNode json = response.getBody();
                JSONObject jsonObject = json.getObject();
                JSONArray array = jsonObject.getJSONArray("results");
                for(int j = 0; j<array.length(); j++) {
                    JSONObject recipe = array.getJSONObject(j);
                    int id = recipe.getInt("id");
                    String imageURL = recipe.getString("image");
                    String title = recipe.getString("title");

                    Recipe newRecipe = new Recipe(title, imageURL, id);
                    recipes.add(newRecipe);
                }

            } catch(UnirestException e) {

            }

        }


        return
    }

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



    public void getRecipes(String ingredients) {

        HttpResponse<JsonNode> response;

        try {
            response = Unirest.get("https://api.spoonacular.com/recipes/findByIngredients")
                    .queryString("apiKey", "cae37f32b37e4c3a9375f05f796efd79")
                    .queryString("ingredients", ingredients)
                    .queryString("number", 2)
                    .queryString("ignorePantry", false)
                    .asJson();


            getRecipeInfo(response);

        } catch (UnirestException e) {

        }
    }

    public void getRecipeInfo(HttpResponse<JsonNode> response) {

        JsonNode json = response.getBody();
        JSONArray recipes = json.getArray();
        StringBuilder ids = new StringBuilder();

        for (int i = 0; i < recipes.length(); i++) {
            JSONObject recipe = recipes.getJSONObject(i);
            int id = recipe.getInt("id");
            String title = recipe.getString("title");
            System.out.println(id);
            System.out.println(title);
            ids.append(id + ",");


        }
        ids.deleteCharAt(ids.lastIndexOf(","));
        System.out.println(ids.toString());

        HttpResponse<JsonNode> response2;

        try {
            response2 = Unirest.get("https://api.spoonacular.com/recipes/informationBulk")
                    .queryString("ids", ids.toString())
                    .queryString("apiKey", "cae37f32b37e4c3a9375f05f796efd79")
                    .asJson();

            //System.out.println(response2.getBody());
            printRecipes(response2);

        } catch (UnirestException e) {

        }


    }

    public void printRecipes(HttpResponse<JsonNode> response2) {

        JsonNode json = response2.getBody();
        JSONArray recipes = json.getArray();

        for (int i = 0; i < recipes.length(); i++) {
            JSONObject recipe = recipes.getJSONObject(i);


            System.out.println(recipe.getString("title"));
            JSONArray ingredients = recipe.getJSONArray("extendedIngredients");
            for (int j = 0; j < ingredients.length(); j++) {
                JSONObject ingredient = ingredients.getJSONObject(j);
                System.out.print(ingredient.getString("name") + ", ");
            }
            System.out.println();
            System.out.println(recipe.getString("summary"));
            System.out.println(recipe.getString("sourceUrl"));
        }
    }
}
