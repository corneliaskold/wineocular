
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeGetter {

    public RecipeGetter(String ingredients) {
        getRecipes(ingredients);
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

        } catch(UnirestException e) {

        }
    }

    public void getRecipeInfo(HttpResponse<JsonNode> response) {

        JsonNode json = response.getBody();
        JSONArray recipes = json.getArray();
        StringBuilder ids = new StringBuilder();

        for(int i = 0; i<recipes.length(); i++) {
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

        } catch(UnirestException e) {

        }



    }

    public void printRecipes(HttpResponse<JsonNode> response2) {

        JsonNode json = response2.getBody();
        JSONArray recipes = json.getArray();

        for(int i = 0; i<recipes.length(); i++) {
            JSONObject recipe = recipes.getJSONObject(i);


            System.out.println(recipe.getString("title"));
            JSONArray ingredients = recipe.getJSONArray("extendedIngredients");
            for(int j = 0; j<ingredients.length(); j++) {
                JSONObject ingredient = ingredients.getJSONObject(j);
                System.out.print(ingredient.getString("name") + ", ");
            }
            System.out.println();
            System.out.println(recipe.getString("summary"));
            System.out.println(recipe.getString("sourceUrl"));
        }
    }



}
