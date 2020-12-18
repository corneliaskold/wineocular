import static spark.Spark.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {


    public static void main(String[] args) {
        port(2020);
        //Gson gson = new Gson();

//        get("/", (req, res) -> {
//
//            //jag bara testade att göra java-objekt till json
//            Recipe recipe = new Recipe();
//            //TODO: hämta recept
//            recipe.title = "Spaghetti";
//            recipe.description = "How to make spaghetti";

            //return gson.toJson(recipe, Recipe.class);

//        });

        HttpResponse<JsonNode> response;

        /*try {
            response = Unirest.get("http://xn--ssongsmat-v2a.nu/w/api.php?action=query&title=gurka&format=json")
                    .queryString("format", "json")
                    .asJson();
            System.out.println(response.getBody());
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        try {
            response = Unirest.get("https://api.spoonacular.com/recipes/findByIngredients?ingredients=apples,flour,sugar&number=2&apiKey=cae37f32b37e4c3a9375f05f796efd79").asJson();
            System.out.println(response.getBody());
        } catch(UnirestException e) {

        }




    }


}