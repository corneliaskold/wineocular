public class Recipe {
    public String title;
    public String[] ingredients;
    public String description;
    public String imageURL;
    public int id;

    public Recipe(String title, String imageURL, int id){
        this.title = title;
        this.imageURL = imageURL;
        this.id = id;
    }

    public Recipe(String title, String imageURL, int id, String description, String[] ingredients) {
        this.title = title;
        this.imageURL = imageURL;
        this.id = id;
        this.description = description;
        this.ingredients = ingredients;

    }

    public Recipe(String title) {
        this.title = title;
    }

    public Recipe() {

    }

    public int getId() {
        return id;
    }

    public String toString() {
        String description = title + " " + id;
        return description;
    }

}
