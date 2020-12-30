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

        //TODO: just nu är model = null, vet inte riktigt vad som ska presenteras på förstasdidan eller om vi använder templates
        get("/", (request, response) -> {
            return new PebbleTemplateEngine().render(
                    new ModelAndView(null, "templates/frontpage.html"));
        });

        get("/:recipes", (request, response) -> {
            //Denna arraylist ger internal error 500, den hämtar inget just nu utan blir null.
            //ArrayList<Recipe> recipes = controller.getRecipeArray(request.params("wine"));

            ArrayList<Recipe> recipes = new ArrayList<>();

            //Testrecept för att kunna visa något i webbläsaren.
            for (int i = 0; i < 5; i++) {
                Recipe recipe = new Recipe();
                recipe.id = 715538 + i;
                recipe.title = "Testrecept" + i;
                recipe.imageURL = "url-för bild";
                recipes.add(recipe);
            }

            // Plockar ut info som ska presenteras för varje recept
            ArrayList<Map> recipeList = new ArrayList<Map>();
            for (Recipe r : recipes) {
                HashMap map = new HashMap();
                map.put("title", r.title);
                map.put("imageURL", r.imageURL);
                map.put("details", "http://localhost:2020/" + r.id);
                recipeList.add(map);
            }

            Map model = new HashMap();
            model.put("recipes", recipeList);


            if (preferredResponseType(request).equals("application/json")) {
                response.type("application/json");
                response.body(gson.toJson(recipeList));
            } else {
                response.body(new PebbleTemplateEngine().render(
                        new ModelAndView(model, "templates/results.html"))
                );
            }
            assert response != null;
            return response.body();

        });

        //returnerar ett recept med id
        get("/:id", (request, response) -> {
            Recipe recipe = controller.getRecipeById(Integer.parseInt(request.params("id")));

            HashMap recipeMap = new HashMap();
            recipeMap.put("title", recipe.title);
            recipeMap.put("ingredients", recipe.ingredients);
            recipeMap.put("description", recipe.description);
            recipeMap.put("id", recipe.id);
            recipeMap.put("imageURL", recipe.imageURL);

            if (preferredResponseType(request).equals("application/json")) {
                response.type("application/json");
                response.body(gson.toJson(recipe, Recipe.class));
            } else {
                response.body(
                        new PebbleTemplateEngine().render(
                                new ModelAndView(recipeMap, "templates/recipe.html")
                        )
                );
            }

            assert response != null;
            //System.out.println(response.body());
            return response.body();
        });


//        post("/", (req, res) -> {
//            try {
//                Recipe recipe = gson.fromJson(req.body(), Recipe.class);
//                controller.addRecipe(recipe);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            return "";
//        });

    }

    private static String preferredResponseType(Request request) {
        List<String> types = Arrays.asList(request.headers("Accept").split("\\s*,\\s*"));
        for (String type : types) {
            switch (type) {
                case "application/json":
                case "application/xml":
                case "text/html":
                    return type;
                default:
            }
        }
        return types.get(0);
    }
}

