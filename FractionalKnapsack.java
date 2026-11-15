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

public class FractionalKnapsack {

    public static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                double r1 = (double) item1.value / item1.weight;
                double r2 = (double) item2.value / item2.weight;
                return Double.compare(r2, r1);
            }
        });

        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remainingCapacity = capacity - currentWeight;
                totalValue += item.value * ((double) remainingCapacity / item.weight);
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
