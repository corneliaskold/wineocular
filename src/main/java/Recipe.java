import java.util.ArrayList;

public class Recipe {
    public String title;
    public String[] ingredients;
    public String description;
    public String imageURL;
    public int id;
    public ArrayList<String> instructions;

    public Recipe(String title, String imageURL, int id){
        this.title = title;
        this.imageURL = imageURL;
        this.id = id;
    }

    public Recipe(String title, String imageURL, int id, String description, String[] ingredients, ArrayList<String> instructions) {
        this.title = title;
        this.imageURL = imageURL;
        this.id = id;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(String title) {
        this.title = title;
    }

    public Recipe() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        String description = title + " " + id + instructions;
        return description;
    }

}
