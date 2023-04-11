public class Main {
    public static void main(String[] args) throws InterruptedException {

        char[][] myTable = new char[12][12];
        char[][] oppTable = new char[12][12];
        char[][] myOppTable = new char[12][12];

        myTable[0][0] = ' ';
        oppTable[0][0] = ' ';
        myOppTable[0][0] = ' ';

        char j = '1';
        for (int i = 1; i < myTable.length; i++) {
            if (i == 10) {
                myTable[i][0] = '0';
                oppTable[i][0] = '0';
                myOppTable[i][0] = '0';
            } else {
                myTable[i][0] = j;
                oppTable[i][0] = j;
                myOppTable[i][0] = j;
                j++;
            }
        }

        j = 'A';
        for (int i = 1; i < 11; i++) {
            myTable[0][i] = j;
            oppTable[0][i] = j;
            myOppTable[0][i] = j;
            j++;
        }

        for(int i = 0; i < 12; i++) {
            myTable[11][i] = ' ';
            oppTable[11][i] = ' ';
            myOppTable[11][i] = ' ';
            myTable[i][11] = ' ';
            oppTable[i][11] = ' ';
            myOppTable[i][11] = ' ';
        }

//        □ ■

//        View.status(myTable, oppTable, myOppTable);

        View.run(myTable, oppTable, myOppTable);

    }
}

