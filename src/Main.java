public class Main {
    public static void main(String[] args) {
        GAME BWgame = new GAME();
        for (int i = 0; i < 7; i++) {
            System.out.print(BWgame.getBoard()[i] + " ");
        }
        System.out.println("\nBoard length: " + BWgame.BoardLength());

        UCS UcsAlg = new UCS();

        if (BWgame.isGoal()) {
            System.out.println("Goal reached!");
        } else
            System.out.println("Goal not reached!");
        if (UcsAlg.isGoal()) {
            System.out.println("Goal reached!");
        } else
            System.out.println("Goal not reached!");
        for (int i = 0; i < 7; i++) {
            System.out.print(UcsAlg.getBoard()[i] + " ");
        }
        System.out.println("Starting Solving ");
        UCS ucsSolve= new UCS();
        ucsSolve.search();

    }
}