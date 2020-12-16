import static spark.Spark.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        port(2020);
        Gson gson = new Gson();

        get("/", (req, res) -> {

            //jag bara testade att göra java-objekt till json
            Recipe recipe = new Recipe();
            //TODO: hämta recept
            recipe.title = "Spaghetti";
            recipe.description = "How to make spaghetti";

            return gson.toJson(recipe, Recipe.class);
        });

    }
}