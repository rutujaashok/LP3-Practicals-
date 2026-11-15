import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Item {
    int weight;
    int value;
    int index;

    Item(int weight, int value, int index) {
        this.weight = weight;
        this.value = value;
        this.index = index;
    }
}

// Node structure for the state space tree
class Node {
    int level;    // Level of the node in the decision tree (index of item)
    int profit;   // Total profit of items included up to this node
    int weight;   // Total weight of items included up to this node
    double bound; // Upper bound of maximum profit in subtree of this node

    Node(int level, int profit, int weight) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
    }
}

public class KnapsackBranchAndBound {

    // Function to calculate the upper bound profit using fractional knapsack greedy approach
    static double calculateBound(Node u, int n, int capacity, Item[] items) {
        // If current weight is already more than capacity, return 0 bound
        if (u.weight >= capacity) {
            return 0;
        }

        double profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        // Grasp items greedily as long as they fit completely
        while (j < n && totalWeight + items[j].weight <= capacity) {
            totalWeight += items[j].weight;
            profitBound += items[j].value;
            j++;
        }

        // If there is still capacity, take the fractional part of the next item
        if (j < n) {
            profitBound += (double) (capacity - totalWeight) * items[j].value / items[j].weight;
        }

        return profitBound;
    }

    // Solves the 0/1 knapsack problem using Branch and Bound
    public static double getMaxProfit(int capacity, Item[] items, int n) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                return Double.compare(r2, r1);
            }
        });

        // Max priority queue to store nodes ordered by their upper bounds
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return Double.compare(b.bound, a.bound);
            }
        });

        Node u, v;

        // Dummy node at level -1 (root)
        v = new Node(-1, 0, 0);
        v.bound = calculateBound(v, n, capacity, items);
        pq.add(v);

        double maxProfit = 0;

        while (!pq.isEmpty()) {
            // Pop the node with the highest upper bound
            v = pq.poll();

            // If the bound is greater than maxProfit, explore further
            if (v.bound > maxProfit) {
                // Move to the next level (next item)
                int nextLevel = v.level + 1;

                // --- Case 1: Include the current item ---
                u = new Node(nextLevel, v.profit + items[nextLevel].value, v.weight + items[nextLevel].weight);

                // If weight of node is <= capacity and profit is greater than maxProfit, update maxProfit
                if (u.weight <= capacity && u.profit > maxProfit) {
                    maxProfit = u.profit;
                }

                // Calculate upper bound for the 'include' node
                u.bound = calculateBound(u, n, capacity, items);

                // If upper bound is greater than maxProfit, add to priority queue
                if (u.bound > maxProfit) {
                    pq.add(u);
                }

                // --- Case 2: Exclude the current item ---
                u = new Node(nextLevel, v.profit, v.weight);
                u.bound = calculateBound(u, n, capacity, items);

                // If upper bound is greater than maxProfit, add to priority queue
                if (u.bound > maxProfit) {
                    pq.add(u);
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter the value and weight for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " - Value: ");
            int value = scanner.nextInt();
            System.out.print("Item " + (i + 1) + " - Weight: ");
            int weight = scanner.nextInt();

            items[i] = new Item(weight, value, i);
        }

        System.out.print("Enter the maximum capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        double maxProfit = getMaxProfit(capacity, items, n);
        System.out.println("\nMaximum profit we can obtain = " + maxProfit);

        scanner.close();
    }
}

/*output:
PS C:\Users\Rutuja\OneDrive\Documents> javac KnapsackBranchAndBound.java
PS C:\Users\Rutuja\OneDrive\Documents> java KnapsackBranchAndBound
Enter the number of items: 3
Enter the value and weight for each item:
Item 1 - Value: 10
Item 1 - Weight: 8
Item 2 - Value: 16
Item 2 - Weight: 7
Item 3 - Value: 25
Item 3 - Weight: 12
Enter the maximum capacity of the knapsack: 14

Maximum profit we can obtain = 25.0
*/
