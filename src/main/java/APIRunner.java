import com.google.gson.Gson;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
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

        post("/", (req, res) -> {
            try {
                Recipe recipe = gson.fromJson(req.body(), Recipe.class);
                controller.addRecipe(recipe);
            } catch (Exception e) {
                System.out.println(e);
            }
            return "";
        });


        //returnerar ett json-objekt innehållande en array med flera recept baserade på ingredienser i säsong
        get("/:recipes", (request, response) -> {
            ArrayList<Recipe> recipes = controller.getRecipeArray(request.params("wine"));
            ArrayList<Map> recipeList = new ArrayList<Map>();

            for (Recipe r : recipes) {
                HashMap map = new HashMap();
                map.put("title", r.title);
                map.put("imageURL", r.imageURL);
                map.put("details", "http://localhost:2020/" + r.id);
                recipeList.add(map);
            }


            if (preferredResponseType(request).equals("application/json")) {
                response.type("application/json");
                response.body(gson.toJson(recipes, Recipe.class));
            } else {

                Map model = new HashMap();
                model.put("recipes", recipeList);
                response.body(new PebbleTemplateEngine().render(
                        new ModelAndView(model, "templates/results.html"))
                );
            }

            assert response != null;
            System.out.println(response.body());
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


// Att göra - få upp alla recept i receptboken som matchar det vin vi sökt på
// skicka en get till en lista på recept  - för att presentera resultatet
// Cornelia skriver en receptbok - api behöver metod att hämta receptboken
// get - för att hämta receptboken så att html-koden kan hämta den.

// receptboken kommer skapas som ett json-objekt till hemsidan,
//
