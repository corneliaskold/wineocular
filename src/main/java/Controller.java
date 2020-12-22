public class Controller {
    private RecipeBook recipeBook;
    private RecipeGetter recipeGetter;
    private FoodInSeason foodInSeason;

    public Controller(){
        recipeBook = new RecipeBook(this);
        foodInSeason = new FoodInSeason();


        //RecipeGetter rg = new RecipeGetter("scallions,+garlic,+pasta,+cauliflower");
    }

    public RecipeBook getRecipeBook() {
        return recipeBook;
    }

    public Recipe getRecipe(int id){
       return recipeBook.getRecipe(id);
    }

    public Recipe[] getRecipeArray(){
        return recipeBook.getRecipeArray();
    }

    public void addRecipe(Recipe recipe){
        recipeBook.addRecipe(recipe.id, recipe);
    }

    // Hämtar en Arraylist med ingredienser i säsong, att skicka vidare till recepiegetter
    public void getFoodInSeason(){
        foodInSeason.getIngredientsInSeason();
    }
}
