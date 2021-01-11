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

    public ArrayList<Recipe> getRecipeArray(String wine) {
        System.out.println("Controller getRecipeArray(): " + wine);
        for (String s : getFoodInSeason()){
            System.out.println(s);
        }

        System.out.println(recipeGetter.getByWineAndSeason(wine, getFoodInSeason()));
        return recipeGetter.getByWineAndSeason(wine, getFoodInSeason());
    }

    public ArrayList<String> getFoodInSeason() {
        return foodInSeason.getIngredientsInSeason();
    }

}

