
import java.util.*;

public class Fibonacci {

    // Non-Recursive (Iterative) Method
    public static void iterativeFibonacci(int n) {

        int first = 0;
        int second = 1;

        System.out.print("Non-Recursive Fibonacci Series: ");

        for (int i = 1; i <= n; i++) {

            System.out.print(first);

            if (i < n) {
                System.out.print(", ");
            }

            int next = first + second;
            first = second;
            second = next;
        }

        System.out.println();
    }

    // Recursive Method
    public static int recursiveFibonacci(int n) {

        if (n <= 0)
            return 0;

        if (n == 1)
            return 1;

        return recursiveFibonacci(n - 1)
             + recursiveFibonacci(n - 2);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Enter number greater than 0");
        }
        else {

            // Calling non-recursive method
            iterativeFibonacci(n);

            // Calling recursive method
            System.out.print("Recursive Fibonacci Series: ");

            for (int i = 0; i < n; i++) {

                System.out.print(recursiveFibonacci(i));

                if (i < n - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }

        sc.close();
    }
}
