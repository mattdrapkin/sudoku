/* Sudoku.java
 * Matt Drapkin and Skyla Qian, 2021
 *
 * This program implements a standard Sudoku game.
 *
 * The user is provided with a random Sudoku board. They are prompted
 * to insert values into different coordinates, while allowing the user
 * to delete previous moves as they progress. The top left coordinate
 * is represented by (0, 0) and the bottom right coordinate is represented
 * by (8, 8). During user input, they are asked what number they would
 * like to input. Then, they are asked where they would like to insert
 * the number in the form [row] [col]. Finally, they are given the ability
 * to delete numbers.
 *
 *
 * Compile: javac-introcs Sudoku.java
 * Run: java-introcs Sudoku (if you want a random board)
 *      java-introcs Sudoku [Sudoku file name] (if you want a specific board]
 *
 *
 *  Commenting formatting inspired by Alfredo Velasco in his Loom tutorials!
 *
 */

public class Sudoku {

    private static final int DIM = 9; // dimensions of the 9 x 9 board
    private static String[][] board; // current state of the Sudoku board
    private static boolean[][] spotTaken; // filled spots in the Sudoku board
    private static boolean[][] originalBoard; // non-deletable spots of the board
    private static int[] numberUsage; // tracks what values have been used in each
                                        // row, column, and mini-square

    // creates a random Sudoku board
    public Sudoku() {
        board = new String[DIM][DIM];
        spotTaken = new boolean[DIM][DIM];
        originalBoard = new boolean[DIM][DIM];
        numberUsage = new int[DIM];
        // generates random Sudoku board from a list of 46 boards
        int randomBoardNumber = StdRandom.uniform(1, 47);
        readBoard("s" + randomBoardNumber + ".txt");
    }

    // constructor for testing
    public Sudoku(String fileName) {
        board = new String[DIM][DIM];
        spotTaken = new boolean[DIM][DIM];
        originalBoard = new boolean[DIM][DIM];
        numberUsage = new int[DIM];
        readBoard(fileName);
    }

    // reads the board in from standard input and returns nothing
    public static void readBoard(String fileName) {
        In reader = new In(fileName);
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                board[i][j] = reader.readString();
                // replaces 0s with underscores
                if (board[i][j].equals("0")) {
                    board[i][j] = "_";
                }
                // indicates the non-deletable spots and the taken spots
                else {
                    spotTaken[i][j] = true;
                    originalBoard[i][j] = true;
                }
            }
        }
    }

    // prints the board to standard output and returns nothing
    public static void printBoard() {
        int NUM_HORIZONTAL_DIVIDERS = 20;
        for (int i = 0; i < DIM; i++) {
            if (i == 3 || i == 6) {
                // creates horizontal dividers
                for (int a = 0; a < NUM_HORIZONTAL_DIVIDERS; a++) {
                    StdOut.print("-");
                }
                StdOut.println();
            }
            for (int j = 0; j < DIM; j++) {
                StdOut.print(board[i][j] + " ");
                // creates vertical dividers
                if (j % 3 == 2 && j != 8) StdOut.print("|");
            }
            StdOut.println();
        }
        StdOut.println();
    }

    // updates board based on user-input and returns nothing
    public static void updateBoard(int num, int row, int col) {
        board[row][col] = "" + num;
        spotTaken[row][col] = true;
    }

    // deletes numbers from board based on user-input and returns nothing
    public static void deleteBoard(int row, int col) {
        if (originalBoard[row][col]) {
            StdOut.println("Sorry! You cannot delete that value.");
        }
        else {
            board[row][col] = "_";
            spotTaken[row][col] = false;
        }
    }

    // resets numberUsage array and returns nothing
    private static void resetChecker(int[] checker) {
        // mutates the checker array by setting all elements to 0
        for (int k = 0; k < DIM; k++) {
            checker[k] = 0;
        }
    }

    // checks each "mini" 3x3 square and returns nothing
    private static void checkMiniSquare(int row, int col, int rMin, int rMax,
                                       int cMin, int cMax, int[] checker) {
        // mutates the checker array if the value exists in the board
        if (row >= rMin && row < rMax && col >= cMin && col < cMax) {
            for (int i = rMin; i < rMax; i++) {
                for (int j = cMin; j < cMax; j++) {
                    String value = board[i][j];
                    if (!value.equals("_")) {
                        checker[Integer.parseInt(value) - 1]++;
                    }
                }
            }
        }
    }

    // checks for conflicts in the board and returns whether there is a conflict
    public static boolean isAccurate(int col, int row) {
        int MAX_TIMES_USED = 1;
        resetChecker(numberUsage);
        // checks for conflicts in each row
        for (int j = 0; j < DIM; j++) {
            String value = board[col][j];
            if (!value.equals("_")) {
                numberUsage[Integer.parseInt(value) - 1]++;
            }
        }

        for (int k = 0; k < DIM; k++) {
            if (numberUsage[k] > MAX_TIMES_USED) {
                return false;
            }
        }

        resetChecker(numberUsage);

        // checks for conflicts in each column
        for (int i = 0; i < DIM; i++) {
            String value = board[i][row];
            if (!value.equals("_")) {
                numberUsage[Integer.parseInt(value) - 1]++;
            }
        }

        for (int k = 0; k < DIM; k++) {
            if (numberUsage[k] > MAX_TIMES_USED) {
                return false;
            }
        }

        resetChecker(numberUsage);

        // checks for conflicts in each mini-square
        int NEXT_MINISQUARE = 3;
        for (int i = 0; i < DIM; i += NEXT_MINISQUARE) {
            for (int j = 0; j < DIM; j += NEXT_MINISQUARE) {
                checkMiniSquare(row, col, j, j + NEXT_MINISQUARE, i,
                        i + NEXT_MINISQUARE, numberUsage);
            }
        }

        for (int k = 0; k < DIM; k++) {
            if (numberUsage[k] > MAX_TIMES_USED) {
                return false;
            }
        }

        resetChecker(numberUsage);

        // returns true if there are no conflicts
        return true;
    }

    // checks if the board is complete and returns whether or not it is complete
    public static boolean checkComplete() {
        // checks to make sure the board is completely filled in
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (board[i][j].equals("_")) {
                    return false;
                }
            }
        }
        return true;
    }

    // represents the user-interface and returns nothing
    public static void startGame() {
        boolean isComplete = false;
        boolean validNumber = false;
        boolean validCoordinate = false;
        boolean wantToDelete = true;
        int MIN_NUMBER = 1;
        int MAX_NUMBER = 9;
        int MIN_ROW_COL = 0;
        int MAX_ROW_COL = 8;
        // allows for user-input
        while (!isComplete) {

            int num = 0;
            int row = 0;
            int col = 0;

            // gets a valid number from user-input
            while (!validNumber) {
                StdOut.print("\nNumber: ");
                num = StdIn.readInt();
                if (num >= MIN_NUMBER && num <= MAX_NUMBER) {
                    validNumber = true;
                }
                else {
                    StdOut.println("Please enter a valid number.");
                }
            }

            // gets a valid coordinate from user-input
            while (!validCoordinate) {
                StdOut.print("\nCoordinate: ");
                row = StdIn.readInt();
                col = StdIn.readInt();
                if (row >= MIN_ROW_COL && row <= MAX_ROW_COL && col >= MIN_ROW_COL
                        && col <= MAX_ROW_COL && !spotTaken[row][col]) {
                    validCoordinate = true;
                    updateBoard(num, row, col);
                }
                else {
                    StdOut.println("Please enter a valid coordinate.");
                }
            }

            // informs the user if there are conflicts and removes the inputted value
            if (!isAccurate(row, col)) {
                StdOut.println("\nSorry, that's incorrect!  Please try again.\n");
                deleteBoard(row, col);
            }
            printBoard();
            validNumber = false;
            validCoordinate = false;
            isComplete = checkComplete();

            // ends the game if the user wins
            if (isComplete) {
                StdOut.println("Congratulations! You have solved the Sudoku board!");
                break;
            }

            // allows the user to delete numbers from the board
            while (wantToDelete) {
                StdOut.print("Would you like to delete any numbers? "
                                     + "Yes (y) or No (n)? ");
                String willDelete = StdIn.readString();
                if (willDelete.equals("y")) {
                    StdOut.println("\nWhat coordinate would you like to clear?");
                    int deletedRow = StdIn.readInt();
                    int deletedColumn = StdIn.readInt();
                    deleteBoard(deletedRow, deletedColumn);
                    printBoard();
                }
                else {
                    wantToDelete = false;
                }
            }
            wantToDelete = true;
        }
    }

    public static void main(String[] args) {
        // Sudoku board is either chosen by the user or given randomly
        // try-catch referenced from https://www.w3schools.com/java/java_try_catch.asp
        try {
            String file = args[0];
            Sudoku sudoku = new Sudoku(file);
        }
        catch (ArrayIndexOutOfBoundsException exception) {
            Sudoku sudoku = new Sudoku();
        }

        StdOut.println("Welcome to Sudoku! To begin, please select: ");
        StdOut.println("(1) Start - enter \"s\"\n(2) Quit - enter \"q\"\n" +
                "(3) Test Mode - \"t\"");

        String input = StdIn.readString();

        if (input.equals("q")) {
            StdOut.println("Thanks for playing!");
        }
        // testing two custom functions
        else if (input.equals("t")) {
            // TEST 1 FOR printBoard()
            // should print a 9x9 Sudoku board
            printBoard();
            // TEST 1 FOR isAccurate()
            // should return true
            StdOut.println("Is the first row, column, and mini-square accurate? — "
            + isAccurate(0, 0));

            updateBoard(2, 0, 0);

            // TEST 2 FOR printBoard()
            // should print a 9x9 Sudoku board with 2 in upper left corner
            printBoard();

            // TEST 2 FOR isAccurate()
            // should return false
            StdOut.println("\"Is the first row, column, and mini-square NOW " +
                    "accuarte? — \"\n" + isAccurate(0, 0));

        }
        else {
            StdOut.println("Coordinates should be entered in the form --> " +
                    "\"row column\", with 0 0 representing the top left coordinate." +
                    " Good luck!");
            printBoard();
            startGame();
        }
    }
}
