import com.google.gson.Gson;
import spark.ModelAndView;
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
            ArrayList<Recipe> recipes = controller.getRecipeArray(request.params("grape"));
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
         * Endpoint for specific recipe based on parameter id that should match an existing recipe.id
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

