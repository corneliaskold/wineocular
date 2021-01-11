import java.util.ArrayList;

/**
 * The type Controller.
 */
public class Controller {
    private RecipeGetter recipeGetter;
    private FoodInSeason foodInSeason;

    /**
     * Instantiates a new Controller.
     */
    public Controller() {
        foodInSeason = new FoodInSeason();
        recipeGetter = new RecipeGetter();
    }

    /**
     * Gets recipe by id.
     *
     * @param id the id of the recipe
     * @return the recipe by id as Recipe object
     */
    public Recipe getRecipeById(int id) {
        return recipeGetter.getById(id);
    }

    /**
     * Gets recipe array.
     *
     * @param wine the wine
     * @return an ArrayList with Recipe matching wine
     */
    public ArrayList<Recipe> getRecipeArray(String wine) {
        System.out.println("Controller getRecipeArray(): " + wine);
        for (String s : getFoodInSeason()){
            System.out.println(s);
        }

        System.out.println(recipeGetter.getByWineAndSeason(wine, getFoodInSeason()));
        return recipeGetter.getByWineAndSeason(wine, getFoodInSeason());
    }

    /**
     * Gets food in season.
     *
     * @return ingredients as String object, in an ArrayList
     */
    public ArrayList<String> getFoodInSeason() {
        return foodInSeason.getIngredientsInSeason();
    }

}

