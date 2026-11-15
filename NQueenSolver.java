import java.util.Scanner;

public class NQueenSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the value of N (size of the board): ");
        int n = scanner.nextInt();
        
        if (n <= 0) {
            System.out.println("Please enter a positive integer.");
            return;
        }

        int[] board = new int[n]; // board[i] stores the column index for row i
        // Initialize board with -1 to indicate no queen is placed yet
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }

        printBoard(board, n);

        // Allow the user to place queens row by row
        for (int row = 0; row < n; row++) {
            boolean validPlacement = false;
            
            while (!validPlacement) {
                System.out.print("Enter column (0 to " + (n - 1) + ") to place queen in Row " + row + ": ");
                int col = scanner.nextInt();
                
                // Check if column is within bounds
                if (col < 0 || col >= n) {
                    System.out.println("Invalid input! Column must be between 0 and " + (n - 1) + ".");
                    continue;
                }
                
                // Check if the position conflicts with already placed queens
                if (isSafe(row, col, board)) {
                    board[row] = col;
                    validPlacement = true;
                    System.out.println(" Safe position! Queen placed successfully.");
                    printBoard(board, n);
                } else {
                    System.out.println(" Wrong position! This queen will be attacked by another queen. Try a different column.");
                }
            }
        }
        
        System.out.println("\n Congratulations! All " + n + " queens placed successfully without conflicts!");
        scanner.close();
    }

    // Helper method to check if a queen can be safely placed at (row, col)
    private static boolean isSafe(int row, int col, int[] board) {
        for (int i = 0; i < row; i++) {
            // Check if column is the same or if it lies on the same diagonal
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    // Helper method to format and print the chessboard
    private static void printBoard(int[] board, int n) {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
/*Output:PS C:\Users\Rutuja\OneDrive\Documents> java NQueenSolver
Enter the value of N (size of the board): 4

Current Board:
. . . .
. . . .
. . . .
. . . .

Enter column (0 to 3) to place queen in Row 0: 1
 Safe position! Queen placed successfully.

Current Board:
. Q . .
. . . .
. . . .
. . . .

Enter column (0 to 3) to place queen in Row 1: 3
 Safe position! Queen placed successfully.

Current Board:
. Q . .
. . . Q
. . . .
. . . .

Enter column (0 to 3) to place queen in Row 2: 0
 Safe position! Queen placed successfully.

Current Board:
. Q . .
. . . Q
Q . . .
. . . .

Enter column (0 to 3) to place queen in Row 3: 2
 Safe position! Queen placed successfully.

Current Board:
. Q . .
. . . Q
Q . . .
. . Q .


 Congratulations! All 4 queens placed successfully without conflicts!
 */

 
/*import java.util.Scanner;

public class NQueenSolver {

    // Check whether placing a queen is safe
    static boolean isSafe(int[][] board, int row, int col, int n) {

        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Solve using backtracking
    static boolean solve(int[][] board, int row, int n) {

        // All queens are placed
        if (row == n)
            return true;

        for (int col = 0; col < n; col++) {

            if (isSafe(board, row, col, n)) {

                // Place queen
                board[row][col] = 1;

                // Place remaining queens
                if (solve(board, row + 1, n))
                    return true;

                // Backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Enter a positive number.");
        } 
        else {

            int[][] board = new int[n][n];

            boolean result = false;

            // Try placing the first queen
            for (int col = 0; col < n; col++) {

                board[0][col] = 1;

                if (solve(board, 1, n)) {
                    result = true;
                    break;
                }

                board[0][col] = 0;
            }

            if (result) {
                System.out.println("N-Queens Solution:");

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(board[i][j] + " ");
                    }
                    System.out.println();
                }
            } 
            else {
                System.out.println("No solution exists.");
            }
        }

        sc.close();
    }
}

Output:
PS C:\Users\Rutuja\OneDrive\Documents> javac NQueenSolver.java
PS C:\Users\Rutuja\OneDrive\Documents> java NQueenSolver
Enter value of N: 4
N-Queens Solution:
0 1 0 0
0 0 0 1
1 0 0 0
0 0 1 0
*/
