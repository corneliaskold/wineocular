import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class FoodInSeason {
    private String currentMonth;
    private ArrayList<String> ingredientsInSeason;

    public FoodInSeason() {
        currentMonth = new CurrentMonth().getCurrentMonth();
        ingredientsInSeason = new ArrayList<>();
    }

    public void getFoodsInSeason() {
        HttpResponse<JsonNode> response;

        try {
            response = Unirest.get("http://xn--ssongsmat-v2a.nu/w/api.php?action=ask&query=[[Concept:" +
                    currentMonth +
                    "]]%7C?engelska")
                    .queryString("format", "json")
                    .asJson();


            JsonNode body = response.getBody();
            System.out.println("Som jsonnode: " + body);

            JSONObject bodyObject = body.getObject();
            System.out.println("som JSONobject: " + bodyObject);

            JSONObject query = bodyObject.getJSONObject("query");
            System.out.println("'query': " + query);

            JSONObject results = query.getJSONObject("results");
            System.out.println("'results': " + results.keys().toString());


            JSONObject jsonObject = results;
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                if (jsonObject.get(key) instanceof JSONObject) {
                    JSONObject ingrediens = results.getJSONObject(key);
                    JSONObject printouts = ingrediens.getJSONObject("printouts");
                    JSONArray arrayEngelska = printouts.getJSONArray("Engelska");

                    for (Object word : arrayEngelska
                    ) {
                        ingredientsInSeason.add(word.toString());
                    }
                }
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getIngredientsInSeason() {
        return ingredientsInSeason;
    }


    public static void main(String[] args) {
        FoodInSeason prog = new FoodInSeason();
        prog.getFoodsInSeason();
        for (String ingredient: prog.getIngredientsInSeason()
             ) {
            System.out.println(ingredient);
        }
    }


}