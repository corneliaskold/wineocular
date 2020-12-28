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

    public Recipe() {

    }

    public String toString() {
        String description = title + " " + id;
        return description;
    }

}
