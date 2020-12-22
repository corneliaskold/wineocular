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
                    new ModelAndView(null, "templates/index.html"));
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
