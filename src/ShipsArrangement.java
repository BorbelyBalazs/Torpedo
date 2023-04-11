public class ShipsArrangement {



    public static void createRandomTables(char[][] myTable, char[][] oppTable, char[][] myOppTable) {

        for (int i = 1; i < 11; i++) {
            for (int k = 1; k < 11; k++) {
                myTable[i][k] = '_';
                oppTable[i][k] = '_';
                myOppTable[i][k] = '_';
            }
        }

        createTables(myTable, 5);
        createTables(myTable, 4);
        createTables(myTable, 3);
        createTables(myTable, 2);

        createTables(oppTable, 5);
        createTables(oppTable, 4);
        createTables(oppTable, 3);
        createTables(oppTable, 2);

        View.status(myTable, oppTable, myOppTable);
    }

    public static void createTables(char[][] table, int size) {

        int x, y;
        do {
            x = (int) (Math.random() * 10) + 1;
            y = (int) (Math.random() * 10) + 1;
        } while (table[x][y] == '■' || table[x][y - 1] == '■' || table[x][y + 1] == '■' ||
                table[x + 1][y] == '■' || table[x + 1][y - 1] == '■' || table[x + 1][y + 1] == '■' ||
                table[x - 1][y] == '■' || table[x - 1][y - 1] == '■' || table[x - 1][y + 1] == '■');


        int i = x;
        int j = y;

        while (true) {
            int d = (int) (Math.random() * 4);

            if (d == 0 && x - size >= 0 &&
                    checkShips1(table, size, x, y)) {
                for (; x > i - size; x--) {
                    table[x][y] = '■';
                }
                break;


            } else if (d == 1 && y + size <= 11 &&
                    checkShips2(table, size, x, y)) {
                for (; y < j + size; y++) {
                    table[x][y] = '■';
                }
                break;


            } else if (d == 2 && x + size <= 11 &&
                    checkShips3(table, size, x, y)) {
                for (; x < i + size; x++) {
                    table[x][y] = '■';
                }
                break;


            } else if (d == 3 && y - size >= 0 &&
                    checkShips4(table, size, x, y)) {
                for (; y > j - size; y--) {
                    table[x][y] = '■';
                }
                break;
            }
        }
    }

    private static boolean checkShips1(char[][] table, int size, int x, int y) {

        boolean b = false;
        for (int i = 0; i <= size; i++) {
            b = table[x-i][y] != '■' && table[x-i][y-1] != '■' && table[x-i][y+1] != '■';
            if (!b) {
                break;
            }
        }
        return b;
    }

    private static boolean checkShips2(char[][] table, int size, int x, int y) {

        boolean b = false;
        for (int i = 0; i <= size; i++) {
            b = table[x][y+i] != '■' && table[x+1][y+i] != '■' && table[x-1][y+i] != '■';
            if (!b) {
                break;
            }
        }
        return b;
    }

    private static boolean checkShips3(char[][] table, int size, int x, int y) {

        boolean b = false;
        for (int i = 0; i <= size; i++) {
            b = table[x+i][y] != '■' && table[x+i][y+1] != '■' && table[x+i][y-1] != '■';
            if (!b) {
                break;
            }
        }
        return b;
    }

    private static boolean checkShips4(char[][] table, int size, int x, int y) {

        boolean b = false;
        for (int i = 0; i <= size; i++) {
            b = table[x][y-i] != '■' && table[x-1][y-i] != '■' && table[x+1][y-i] != '■';
            if (!b) {
                break;
            }
        }
        return b;
    }
}
