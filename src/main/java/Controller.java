import com.google.gson.Gson;
import java.util.ArrayList;

public class Controller {
    private RecipeGetter recipeGetter;
    private FoodInSeason foodInSeason;
    private String currentSeason;

    public Controller() {
        foodInSeason = new FoodInSeason();
        recipeGetter = new RecipeGetter();
        currentSeason = foodInSeason.getcurrentSeason();
    }

    public String getCurrentSeason() {
        return currentSeason;
    }

    public Recipe getRecipeById(int id) {
        return recipeGetter.getById(id);
    }
}

