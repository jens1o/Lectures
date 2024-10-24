/**
 * Die Sattelpunkte einer Matrix sind die Elemente der Matrix (n x m), die in Ihrer Zeile 
 * am kleinsten sind und in der Spalte am größten.
 */
void printSaddlePoints(int [][] b) { // VERIFY!!!!
    final var ROWS = b.length;
    final var COLS = b[0].length;
    for (int i = 0; i < ROWS; i++) { // für jede Zeile
        // 1. finde in jeder Zeile die Spalten mit dem Minimum
        int cols = 0; // Anzahl der Spalten, die das Minimum enthalten
        int[] col = new int[COLS]; // Spalten mit dem Minimum
        int min = Integer.MAX_VALUE; // Aktuelles Minimum
        assert b[i].length == COLS; // Sicherstellung, dass die Matrix "b" rechteckig ist
        for (int j = 0; j < COLS; j++) {
            if (b[i][j] < min) {
                min = b[i][j];
                cols = 1;
                col[0] = j;
            } else if (b[i][j] == min) {
                col[cols++] = j;
            }
        }
        // 2. prüfe ob das Minimum/die Minima in der Zeile auch die Maxima der Spalte sind
        cols: for (int j = 0; j < cols; j++) {
            for (int k = 0; k < ROWS; k++) {
                if (b[k][col[j]] > min) {
                    continue cols;
                }
            }
            println("Sattelpunkt: " + "(" + i + "," + col[j] + ") = " + min);
        }
    }
}

void printToConsole(int [][] c) {
    //println(Arrays.deepToString(c));
    for (int i = 0; i < c.length; i++) {
        println(Arrays.toString(c[i]));
    }
}

int[][] initMatrix(int[][] a) {
    for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[i].length; j++) {
            a[i][j] = Integer.parseInt(readln("a[" + i + "][" + j + "] = "));
        }
    }
    return a;
}

void main() {
    /*
    final int[][] a = {
        {1, 0, 0},
        {0, -1, -3},
        {10, -2, -4}
    };
    printToConsole(a);
    */

    int m = Integer.parseInt(readln("Anzahl der Zeilen von Matrix a: "));
    int n = Integer.parseInt(readln("Anzahl der Spalten von Matrix a: "));

    final int[][] a = initMatrix(new int[m][n]);
    printToConsole(a);
    println("Sattelpunkte:");
    printSaddlePoints(a);
}