import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.template.pebble.PebbleTemplateEngine;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;
public class APIRunner {
    public APIRunner(){
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
            Recipe recipe = controller.getRecipe(Integer.parseInt(request.params("id")));

            if (preferredResponseType(request).equals("application/json")) {
                response.type("application/json");
                response.body(gson.toJson(recipe, Recipe.class));
            }
            assert response != null;
            System.out.println(response.body());
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


        //returnerar ett json-objekt innehållande en array med flera recept baserade på inggredienser i säsong
        get("/:recipes", (request, response) -> {
            Recipe[] recipes = controller.getRecipeArray();

            if (preferredResponseType(request).equals("application/json")) {
                response.type("application/json");
                response.body(gson.toJson(recipes, RecipeBook.class));
            }
            assert response != null;
            System.out.println(response.body());
            return response.body();
        });

    }

    private static String preferredResponseType(Request request) {
        List<String> types = Arrays.asList(request.headers("Accept").split("\\s*,\\s*"));
        for (String type: types) {
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
