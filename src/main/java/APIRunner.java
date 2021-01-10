import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.template.pebble.PebbleTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class APIRunner {

    public APIRunner() {}

    public static void main(String[] args) {
        port(2020);
        staticFileLocation("/");

        Gson gson = new Gson();
        Controller controller = new Controller();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());



        //TODO: just nu är model = null, vet inte riktigt vad som ska presenteras på förstasdidan eller om vi använder templates
        get("/", (request, response) -> {
            return new PebbleTemplateEngine().render(
                    new ModelAndView(null, "templates/frontpage.html"));
        });

        get("/search/:grape", (request, response) -> {
//            ArrayList<Recipe> recipes = controller.getRecipeArray(request.params("grape"));
            ArrayList<Recipe> recipes = new ArrayList<>(); // För test och slippa slösa points hos spoonacular.

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
        get("/recipe/:id",  (request, response) -> {
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

            if (preferredResponseType(request).equals("application/json") || queryParam(request).equals("application/json")) {
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
        // Ibland skickar en klient en lista av format som den önskar.
        // Här splittar vi upp listan och tar bort eventuella mellanslag.
        List<String> types = Arrays.asList(request.headers("Accept").split("\\s*,\\s*"));

        // Gå igenom listan av format och skicka tillbaka det första som vi stöder
        for (String type: types) {
            switch (type) {
                case "application/json":
                case "application/xml":
                case "text/html":
                    return type;
                default:
            }
        }

        // Om vi inte stöder något av formaten, skicka tillbaka det första formatet
        return types.get(0);
    }


    private static String queryParam(Request request){
        String type = "";

        if (request.queryParams("format") == null) {
            type = "text/html";
        } else if (request.queryParams("format").equals("json")){
            type = "application/json";
        }
        return type;
    }


}

