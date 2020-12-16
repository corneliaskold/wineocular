import static spark.Spark.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        get("/hello", (req, res) -> "Hello World");
    }
}