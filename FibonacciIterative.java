import java.util.*;
public class FibonacciIterative {
    public static void printFibonacci(int n) {
        int first = 0;
        int second = 1;
        if (n >= 1) {
            System.out.print(first);
        }
        if (n >= 2) {
            System.out.print(", " + second);
        }
        for (int i = 3; i <= n; i++) {
            int next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n;
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter number upto which fibonacci series wants to display: ");
	n = sc.nextInt();
        if(n<=0){
	    System.out.println("Enter number greater than 0");
        }
        else{
            System.out.println("Fibonacci Series: ");
            printFibonacci(n);
	}
    }
}
