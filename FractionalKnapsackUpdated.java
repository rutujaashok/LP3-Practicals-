import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsackUpdated {

    public static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                double r1 = (double) item1.value / item1.weight;
                double r2 = (double) item2.value / item2.weight;
                return Double.compare(r2, r1);
            }
        });

        System.out.println("\n--- Items Sorted by Value/Weight Ratio ---");
        for (int i = 0; i < items.length; i++) {
            double ratio = (double) items[i].value / items[i].weight;
            System.out.println("Item " + (i + 1) + " -> Value: " + items[i].value + 
                               ", Weight: " + items[i].weight + 
                               ", Ratio: " + String.format("%.2f", ratio));
        }
        System.out.println("------------------------------------------\n");

        double totalValue = 0.0;
        int currentWeight = 0;

        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
                System.out.println("Step " + (i + 1) + ": Took FULL Item (Value: " + item.value + 
                                   ", Weight: " + item.weight + ") | Total Weight So Far: " + 
                                   currentWeight + " | Total Value So Far: " + totalValue);
            } else {
                int remainingCapacity = capacity - currentWeight;
                double fractionValue = item.value * ((double) remainingCapacity / item.weight);
                totalValue += fractionValue;
                currentWeight += remainingCapacity;
                
                // Display intermediate step for fractional item inclusion
                System.out.println("Step " + (i + 1) + ": Took FRACTION (" + remainingCapacity + "/" + 
                                   item.weight + ") of Item (Value: " + item.value + ", Weight: " + 
                                   item.weight + ") -> Added Value: " + String.format("%.2f", fractionValue) + 
                                   " | Total Weight So Far: " + currentWeight + " | Total Value So Far: " + totalValue);
                break;
            }
        }

        return totalValue;
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
            
            items[i] = new Item(value, weight);
        }

        System.out.print("Enter the maximum capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        double maxValue = getMaxValue(items, capacity);
        System.out.println("\nMaximum value we can obtain = " + maxValue);

        scanner.close();
    }
}

/*Output:
PS C:\Users\Rutuja\OneDrive\Documents> javac FractionalKnapsackUpdated.java
PS C:\Users\Rutuja\OneDrive\Documents> java FractionalKnapsackUpdated
Enter the number of items: 4
Enter the value and weight for each item:
Item 1 - Value: 10
Item 1 - Weight: 8
Item 2 - Value: 15
Item 2 - Weight: 6
Item 3 - Value: 25
Item 3 - Weight: 10
Item 4 - Value: 12
Item 4 - Weight: 5
Enter the maximum capacity of the knapsack: 14

--- Items Sorted by Value/Weight Ratio ---
Item 1 -> Value: 15, Weight: 6, Ratio: 2.50
Item 2 -> Value: 25, Weight: 10, Ratio: 2.50
Item 3 -> Value: 12, Weight: 5, Ratio: 2.40
Item 4 -> Value: 10, Weight: 8, Ratio: 1.25
------------------------------------------

Step 1: Took FULL Item (Value: 15, Weight: 6) | Total Weight So Far: 6 | Total Value So Far: 15.0
Step 2: Took FRACTION (8/10) of Item (Value: 25, Weight: 10) -> Added Value: 20.00 | Total Weight So Far: 14 | Total Value So Far: 35.0

Maximum value we can obtain = 35.0 */