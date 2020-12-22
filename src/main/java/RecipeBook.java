import java.util.HashMap;

public class RecipeBook {
    private Recipe[] recipeArray;
    private HashMap<Integer, Recipe> recipeBook;
    private Controller controller;
    private int index;

    //HashMap som samlar recept, key=id
    public RecipeBook(Controller controller){
        this.controller = controller;
        recipeBook = new HashMap<>();
        index = 0;
        recipeArray = new Recipe[index+1];
    }

    public void addRecipe(int id, Recipe recipe){
        recipeBook.put(id, recipe);
        recipeArray[index] = recipe;
        index++;
    }

    public Recipe getRecipe(int id){
       return recipeBook.get(id);
    }

    public Recipe[] getRecipeArray(){
        return recipeArray;
    }

}
