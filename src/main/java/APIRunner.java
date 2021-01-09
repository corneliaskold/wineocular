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
        String currentSeason = controller.getCurrentSeason();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());

        //TODO: just nu är model = null, vet inte riktigt vad som ska presenteras på förstasdidan eller om vi använder templates
        get("/", (request, response) -> {
            return new PebbleTemplateEngine().render(
                    new ModelAndView(null, "templates/frontpage.html"));
        });

        get("/search/:grape", (request, response) -> {
           // System.out.println(request.params("grape"));
//            ArrayList<Recipe> recipes = controller.getRecipeArray(request.params("grape"));
//            System.out.println("test");

//            ArrayList<Recipe> recipes = controller.getRecipeArray("merlot");
//            for (Recipe r : recipes){
//                System.out.println(r.title);
//            }

            ArrayList<Recipe> recipes = new ArrayList<>();

            //Testrecept för att kunna visa något i webbläsaren.
            for (int i = 0; i < 5; i++) {
                Recipe recipe = new Recipe();
                recipe.id = 715537 + i;
                recipe.title = "Te str ece pt fw u ehf uwehfuwef uwehf uwef fuwehf uweh fuweh f" + i;
                recipe.imageURL = "https://spoonacular.com/recipeImages/" + recipe.id + "-312x231.jpg";
                recipes.add(recipe);
            }

            // Plockar ut info som ska presenteras för varje recept
            ArrayList<Map> recipeList = new ArrayList<Map>();

            for (Recipe r : recipes) {
                HashMap map = new HashMap();
                map.put("title", r.title);
                map.put("imageURL", r.imageURL);
                map.put("details", "http://localhost:2020/recipe/" + r.id);

                recipeList.add(map);
            }

            if (preferredResponseType(request).equals("application/json")) {
                response.type("application/json");
                response.body(gson.toJson(recipeList));
            } else {

                Map model = new HashMap();
                model.put("recipes", recipeList);
                model.put("season", controller.getCurrentSeason());
                model.put("grape", request.params("grape"));

                response.body(new PebbleTemplateEngine().render(
                        new ModelAndView(model, "templates/results.html"))
                );
            }
            assert response != null;
            return response.body();

        });

        //returnerar ett recept med id
        get("/recipe/:id", (request, response) -> {
            Recipe recipe = controller.getRecipeById(Integer.parseInt(request.params("id")));

            ArrayList ingredients = new ArrayList();
            for (int i = 0; i < recipe.ingredients.length; i++) {
                ingredients.add(recipe.ingredients[i]);
            }

            HashMap recipeMap = new HashMap();
            recipeMap.put("grape", request.params("grape"));
            recipeMap.put("title", recipe.title);
            recipeMap.put("description", recipe.description);
            recipeMap.put("ingredients", ingredients);
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
            return response.body();
        });
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

