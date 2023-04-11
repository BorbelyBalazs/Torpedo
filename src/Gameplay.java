import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Gameplay {

    static Scanner sc = new Scanner(System.in);
    static int hx, hy;

    public static void game(char[][] myTable, char[][] oppTable, char[][] myOppTable) throws InterruptedException {

        boolean b = true;
        boolean bb = true;
        while (b && bb) {
            b = myTurn(myTable, oppTable, myOppTable);

            if (b) {
                bb = oppTurn(myTable, oppTable, myOppTable);
            }
        }
    }


    private static boolean myTurn(char[][] myTable, char[][] oppTable, char[][] myOppTable) throws InterruptedException {

        System.out.println("Te következel\n Adj meg egy koordinátát (betű, szám)");
        boolean b;

        do {
            b = false;
            String coor = sc.nextLine().toLowerCase();
            int x = Integer.parseInt(String.valueOf(coor.charAt(1)));
            int y = charToNum(coor.charAt(0));

            if (x == 0) {
                x = 10;
            }

            if (oppTable[x][y] == '■') {
                System.out.println("Talált");
                myOppTable[x][y] = 'X';
                oppTable[x][y] = 'X';
                if (checkIsSunken(oppTable)) {
                    System.out.println("Süllyedt");
                    for (int i = 1; i < 11; i++) {
                        for (int j = 1; j < 11; j++) {
                            if (oppTable[i][j] == 'X') {
                                if (oppTable[i][j+1] == '_') {
                                    myOppTable[i][j+1] = '*';
                                }
                                if (oppTable[i][j-1] == '_') {
                                    myOppTable[i][j-1] = '*';
                                }
                                if (oppTable[i+1][j] == '_') {
                                    myOppTable[i+1][j] = '*';
                                }
                                if (oppTable[i-1][j] == '_') {
                                    myOppTable[i-1][j] = '*';
                                }
                                if (oppTable[i+1][j+1] == '_') {
                                    myOppTable[i+1][j+1] = '*';
                                }
                                if (oppTable[i-1][j-1] == '_') {
                                    myOppTable[i-1][j-1] = '*';
                                }
                                if (oppTable[i+1][j-1] == '_') {
                                    myOppTable[i+1][j-1] = '*';
                                }
                                if (oppTable[i-1][j+1] == '_') {
                                    myOppTable[i-1][j+1] = '*';
                                }
                            }
                        }
                    }
                    if (checkEndGame(oppTable)) {
                        System.out.println("Gratulálok, nyertél!\n");
                        return false;
                    }
                }

            } else if (oppTable[x][y] == '_') {
                System.out.println("Nem talált");
                myOppTable[x][y] = 'O';
                oppTable[x][y] = 'O';

            } else {
                System.out.println("Érvénytelen koordináta, kérlek adj meg másikat");
                b = true;
            }
        } while (b);

        TimeUnit.SECONDS.sleep(2);
        View.status(myTable, oppTable, myOppTable);

        return true;
    }


    private static boolean oppTurn(char[][] myTable, char[][] oppTable, char[][] myOppTable) throws InterruptedException {


        System.out.println("A gép következik");

        boolean b = false;
        boolean bb = true;

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                if(myTable[i][j] == 'X') {
                    if(myTable[i][j+1] == '■' || myTable[i][j-1] == '■' ||
                       myTable[i-1][j] == '■' || myTable[i+1][j] == '■') {
                        oppTurnAfterHit(myTable);
                         if (checkEndGame(myTable)) {
                             System.out.println("Sajnálom, vesztettél\n");
                             return false;

                         }
                         b = true;
                        bb = false;
                        break;
                    }
                }
            }
            if (b) {
                break;
            }
        }
        if (bb) {
            oppTurnRandom(myTable);

        }

        TimeUnit.SECONDS.sleep(2);
        View.status(myTable, oppTable, myOppTable);

        return true;
    }




    private static int charToNum(char c) {
        switch (c) {
            case 'a' -> {
                return 1;
            }
            case 'b' -> {
                return 2;
            }
            case 'c' -> {
                return 3;
            }
            case 'd' -> {
                return 4;
            }
            case 'e' -> {
                return 5;
            }
            case 'f' -> {
                return 6;
            }
            case 'g' -> {
                return 7;
            }
            case 'h' -> {
                return 8;
            }
            case 'i' -> {
                return 9;
            }
            case 'j' -> {
                return 10;
            }
        }
        return 0;
    }

    private static boolean checkIsSunken(char[][] table) {

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (table[i][j] == 'X') {
                    if (table[i][j - 1] == '■' || table[i][j + 1] == '■' ||
                            table[i + 1][j] == '■' || table[i - 1][j] == '■') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean checkEndGame(char[][] table) {

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (table[i][j] == '■') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void oppTurnRandom(char[][] myTable) {
        boolean b;
        do {
            b = false;
            int x = (int) (Math.random() * 10) + 1;
            int y = (int) (Math.random() * 10) + 1;

            if (myTable[x][y] == '■') {
                System.out.println("Talált");
                myTable[x][y] = 'X';
                hx = x;
                hy = y;

            } else if (myTable[x][y] == '_') {
                System.out.println("Nem talált");
                myTable[x][y] = 'O';

            } else {
                b = true;
            }
        } while (b);

    }

    private static void oppTurnAfterHit(char[][] myTable) {

       if (!firstCheck(myTable, hx, hy)) {

            boolean bb = false;
            for (int x = 1; x < 11; x++) {
                for (int y = 1; y < 11; y++) {
                    if (myTable[x][y] == 'X') {
                        if (myTable[x][y + 1] == '■' || myTable[x][y - 1] == '■' ||
                                myTable[x - 1][y] == '■' || myTable[x + 1][y] == '■') {

                            if (!firstCheck(myTable, x, y)) {

                                boolean b = true;

                                while (b) {
                                    int d = (int) (Math.random() * 4);

                                    switch (d) {
                                        case 0 -> {
                                            if (myTable[x - 1][y] == '_') {
                                                System.out.println("Nem talált");
                                                myTable[x - 1][y] = 'O';
                                                b = false;
                                            } else if (myTable[x - 1][y] == '■') {
                                                System.out.println("Talált");
                                                myTable[x - 1][y] = 'X';
                                                if (checkIsSunken(myTable)) {
                                                    System.out.println("Süllyedt");
                                                    afterSunk(myTable);

                                                } else {
                                                    hx = x - 1;
                                                }
                                                b = false;
                                            }
                                        }
                                        case 1 -> {
                                            if (myTable[x][y + 1] == '_') {
                                                System.out.println("Nem talált");
                                                myTable[x][y + 1] = 'O';
                                                b = false;
                                            } else if (myTable[x][y + 1] == '■') {
                                                System.out.println("Talált");
                                                myTable[x][y + 1] = 'X';
                                                if (checkIsSunken(myTable)) {
                                                    System.out.println("Süllyedt");
                                                    afterSunk(myTable);

                                                } else {
                                                    hy = y + 1;
                                                }
                                                b = false;
                                            }

                                        }
                                        case 2 -> {
                                            if (myTable[x + 1][y] == '_') {
                                                System.out.println("Nem talált");
                                                myTable[x + 1][y] = 'O';
                                                b = false;
                                            } else if (myTable[x + 1][y] == '■') {
                                                System.out.println("Talált");
                                                myTable[x + 1][y] = 'X';
                                                if (checkIsSunken(myTable)) {
                                                    System.out.println("Süllyedt");
                                                    afterSunk(myTable);

                                                } else {
                                                    hx = x + 1;
                                                }
                                                b = false;
                                            }

                                        }
                                        case 3 -> {
                                            if (myTable[x][y - 1] == '_') {
                                                System.out.println("Nem talált");
                                                myTable[x][y - 1] = 'O';
                                                b = false;
                                            } else if (myTable[x][y - 1] == '■') {
                                                System.out.println("Talált");
                                                myTable[x][y - 1] = 'X';
                                                if (checkIsSunken(myTable)) {
                                                    System.out.println("Süllyedt");
                                                    afterSunk(myTable);

                                                } else {
                                                    hy = y - 1;
                                                }
                                                b = false;
                                            }
                                        }
                                    }
                                }
                                bb = true;
                                break;
                            }
                            bb = true;
                        }
                            }
                        }
                if (bb) {
                    break;
                }

                    }
                }


    }

    private static void afterSunk(char[][] myTable) {

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (myTable[i][j] == 'X') {
                    if (myTable[i][j+1] == '_') {
                        myTable[i][j+1] = '*';
                    }
                    if (myTable[i][j-1] == '_') {
                        myTable[i][j-1] = '*';
                    }
                    if (myTable[i+1][j] == '_') {
                        myTable[i+1][j] = '*';
                    }
                    if (myTable[i-1][j] == '_') {
                        myTable[i-1][j] = '*';
                    }
                    if (myTable[i+1][j+1] == '_') {
                        myTable[i+1][j+1] = '*';
                    }
                    if (myTable[i-1][j-1] == '_') {
                        myTable[i-1][j-1] = '*';
                    }
                    if (myTable[i+1][j-1] == '_') {
                        myTable[i+1][j-1] = '*';
                    }
                    if (myTable[i-1][j+1] == '_') {
                        myTable[i-1][j+1] = '*';
                    }
                }
            }
        }
    }


    private static boolean firstCheck(char[][] myTable, int x, int y) {
        if (myTable[x][y-1] == 'X' && (myTable[x][y+1] == '_' || myTable[x][y+1] == '■')) {

            if (myTable[x][y+1] == '_') {
                System.out.println("Nem talált");
                myTable[x][y+1] = 'O';
            } else if (myTable[x][y+1] == '■') {
                System.out.println("Talált");
                myTable[x][y+1] = 'X';
                if (checkIsSunken(myTable)) {
                    System.out.println("Süllyedt");
                    afterSunk(myTable);

                } else {
                    hy = y + 1;
                }
            }
            return true;
        }

        else if (myTable[x][y+1] == 'X' && (myTable[x][y-1] == '_' || myTable[x][y-1] == '■')) {
            if (myTable[x][y-1] == '_') {
                System.out.println("Nem talált");
                myTable[x][y-1] = 'O';
            } else if (myTable[x][y-1] == '■') {
                System.out.println("Talált");
                myTable[x][y-1] = 'X';
                if (checkIsSunken(myTable)) {
                    System.out.println("Süllyedt");
                    afterSunk(myTable);

                } else {
                    hy = y - 1;
                }
            }
            return true;
        }

        else if (myTable[x+1][y] == 'X' && (myTable[x-1][y] == '_' || myTable[x-1][y] == '■')) {
            if (myTable[x-1][y] == '_') {
                System.out.println("Nem talált");
                myTable[x-1][y] = 'O';
            } else if (myTable[x-1][y] == '■') {
                System.out.println("Talált");
                myTable[x-1][y] = 'X';
                if (checkIsSunken(myTable)) {
                    System.out.println("Süllyedt");
                    afterSunk(myTable);

                } else {
                    hx = x - 1;
                }
            }
            return true;
        }

        else if (myTable[x-1][y] == 'X' && (myTable[x+1][y] == '_' || myTable[x+1][y] == '■')) {
            if (myTable[x+1][y] == '_') {
                System.out.println("Nem talált");
                myTable[x+1][y] = 'O';
            } else if (myTable[x+1][y] == '■') {
                System.out.println("Talált");
                myTable[x+1][y] = 'X';
                if (checkIsSunken(myTable)) {
                    System.out.println("Süllyedt");
                    afterSunk(myTable);

                } else {
                    hx = x + 1;
                }
            }
            return true;
        }
        return false;
    }
}
