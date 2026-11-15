import java.util.*;
public class FibonacciRecursive {
    public static int getFibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return getFibonacci(n - 1) + getFibonacci(n - 2);
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
            System.out.print("Fibonacci Series: ");
            for (int i = 0; i < n; i++) {
                System.out.print(getFibonacci(i));
                if (i < n - 1) { 
                    System.out.print(", ");
                } 
            }
            System.out.println();
	}   
    }
}
