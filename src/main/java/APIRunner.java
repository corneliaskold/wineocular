import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.template.pebble.PebbleTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class APIRunner {

    public APIRunner() {
    }

    public static void main(String[] args) {
        port(2020);
        staticFileLocation("/");

        Gson gson = new Gson();
        Controller controller = new Controller();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());

        get("/", (request, response) -> {
            return new PebbleTemplateEngine().render(
                    new ModelAndView(null, "templates/index.html"));
        });

        /**
         * Endpoint for grape-search
         * Returns a list of recipes that are matched with the actual grape and contains ingredients in season
         */
        get("/search/:grape", (request, response) -> {
            //ArrayList<Recipe> recipes = controller.getRecipeArray(request.params("grape"));

            /**
             * VIKTIGT MEDDELANDE!
             * TODO: detta segment är endast för test och slippa slösa points hos spoonacular
             * Ska tas bort innan koden lämnas in och den bortkommenterade metoden ovan ska användas.
             */
            ArrayList<Recipe> recipes = new ArrayList<>(); // För test och slippa slösa points hos spoonacular.

            //Testrecept för att kunna visa något i webbläsaren.
            for (int i = 0; i < 5; i++) {
                Recipe recipe = new Recipe();
                recipe.id = 715537 + i;
                recipe.title = "Te str ece pt fw u ehf uwehfuwef uwehf uwef fuwehf uweh fuweh f" + i;
                recipe.imageURL = "https://spoonacular.com/recipeImages/" + recipe.id + "-312x231.jpg";
                recipes.add(recipe);
            }

            /**
             * Slut på meddelande...
             */

            ArrayList<Map> recipeList = new ArrayList<Map>();

            for (Recipe r : recipes) {
                HashMap map = new HashMap();
                map.put("title", r.title);
                map.put("imageURL", r.imageURL);
                map.put("details", "http://localhost:2020/recipe/" + r.id);
                recipeList.add(map);
            }

            response.type("application/json");
            response.body(gson.toJson(recipeList));

            assert response != null;
            return response.body();

        });

        /**
         * Endpoint for specific recipe based on recipe.id
         * Returns a recipe-object in json format
         */
        get("/recipe/:id", (request, response) -> {
            Recipe recipe = controller.getRecipeById(Integer.parseInt(request.params("id")));

            response.type("application/json");
            response.body(gson.toJson(recipe, Recipe.class));

            assert response != null;
            return response.body();
        });

    }

}

