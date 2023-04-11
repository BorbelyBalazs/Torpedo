import java.util.Arrays;
import java.util.Scanner;

public class View {

    public static void run(char[][] myTable, char[][] oppTable, char[][] myOppTable) throws InterruptedException {

        Scanner s = new Scanner(System.in);
        System.out.println("Üdvözöllek!\n");
        int choice = 0;

        while (choice != 2) {
            System.out.println("1. Új játék\n" +
                    "2. Kilépés");
            choice = s.nextInt();

            switch (choice) {
                case 1 -> {
                    ShipsArrangement.createRandomTables(myTable, oppTable, myOppTable);
                    Gameplay.game(myTable, oppTable, myOppTable);
                }
                case 2 -> System.out.println("Viszlát!");

                default -> System.out.println("Kérlek a megadott opciók közül válassz!");
            }
        }

    }

    public static void status(char[][] myTable, char[][] oppTable, char[][] myOppTable) {

        System.out.println("Az én táblám");
        System.out.println(Arrays.deepToString(myTable).replace("],", "]\n").
                replace(",", "") + "\n");
//        System.out.println(Arrays.deepToString(oppTable).replace("],", "]\n").
//                replace(",", "") + "\n");
        System.out.println("Az ellenfél táblája");
        System.out.println(Arrays.deepToString(myOppTable).replace("],", "]\n").
                replace(",", "") + "\n");
    }
}
