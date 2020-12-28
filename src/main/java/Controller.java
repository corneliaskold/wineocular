import com.google.gson.Gson;

import javax.sound.midi.Receiver;
import java.util.ArrayList;

public class Controller {
    private RecipeGetter recipeGetter;
    private FoodInSeason foodInSeason;

    public Controller() {
        foodInSeason = new FoodInSeason();
        recipeGetter = new RecipeGetter();
    }

    public Recipe getRecipeById(int id) {
        return recipeGetter.getById(id);
    }

    public String recipeToJson(Recipe recipe) {
        Gson gson = new Gson();
        return gson.toJson(recipe, Recipe.class);
    }

    public ArrayList<Recipe> getRecipeArray(String wine) {
        return recipeGetter.getByWineAndSeason(wine, getFoodInSeason());
    }

    public ArrayList<String> recipeArrayToJson(ArrayList<Recipe> recipeArrayList) {
        Gson gson = new Gson();
        ArrayList<String> jsonRecipeArray = new ArrayList<>();

        for (int i = 0; i <= recipeArrayList.size(); i++) {
            Recipe recipe = recipeArrayList.get(i);
            jsonRecipeArray.add(gson.toJson(recipe, Recipe.class));
        }
        return jsonRecipeArray;
    }

    // Hämtar en Arraylist med ingredienser i säsong, att skicka vidare till recepiegetter
    public ArrayList<String> getFoodInSeason() {
        return foodInSeason.getIngredientsInSeason();
    }

    public void addRecipe(Recipe recipe) {

    }

    public ArrayList<String> getResultList(ArrayList<Recipe> recipeArrayList) {
        ArrayList<String> resultList = new ArrayList<>();
        for (int i = 0; i <= recipeArrayList.size(); i++) {
            RecipeResult recipeResult = new RecipeResult(
                    recipeArrayList.get(i).title,
                    recipeArrayList.get(i).imageURL,
                    recipeArrayList.get(i).id);
            Gson gson = new Gson();
            resultList.add(gson.toJson(recipeResult, RecipeResult.class));
        }
        return resultList;
    }

}

