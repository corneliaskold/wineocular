
public class TestGrapes {
    public static void main(String[] args) {
        System.out.println();

        for (Grapes g : Grapes.values()){
            System.out.println(g.title);
            System.out.println(g.description);
        }
    }
}
