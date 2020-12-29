
public class TestGrapes {
    public static void main(String[] args) {
        System.out.println();

        for (RedGrapes g : RedGrapes.values()){
            System.out.println(g.title);
            System.out.println(g.description);
        }

        for (WhiteGrapes g : WhiteGrapes.values()){
            System.out.println(g.title);
            System.out.println(g.description);
        }
    }
}
