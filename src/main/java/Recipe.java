import java.util.ArrayList;

/**
 * Recipe is used to store data of a recipe, in a format that is able to convert into JSON using Gson.
 */
public class Recipe {

    public String title;
    public String[] ingredients;
    public String description;
    public String imageURL;
    public int id;
    public ArrayList<String> instructions;

    /**
     * Instantiates a new Recipe.
     *
     * @param title    the title
     * @param imageURL the image url
     * @param id       the id
     */
    public Recipe(String title, String imageURL, int id){
        this.title = title;
        this.imageURL = imageURL;
        this.id = id;
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param title        the title
     * @param imageURL     the image url
     * @param id           the id
     * @param description  the description
     * @param ingredients  the ingredients
     * @param instructions the instructions
     */
    public Recipe(String title, String imageURL, int id, String description, String[] ingredients, ArrayList<String> instructions) {
        this.title = title;
        this.imageURL = imageURL;
        this.id = id;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param title the title
     */
    public Recipe(String title) {
        this.title = title;
    }

    /**
     * Instantiates a new Recipe.
     */
    public Recipe() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    public String toString() {
        String description = title + " " + id + instructions;
        return description;
    }

}
