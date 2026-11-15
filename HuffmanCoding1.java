
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    char ch;
    int freq;
    Node left;
    Node right;

    // Leaf node constructor
    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    // Internal node constructor
    Node(int freq, Node left, Node right) {
        this.ch = '\0';
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class HuffmanCoding1 {

    // Step 1: Build Frequency Map
    public static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();

        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        return freqMap;
    }

    // Step 2: Build Huffman Tree
    public static Node buildTree(Map<Character, Integer> freqMap) {

        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Handle single unique character
        if (pq.size() == 1) {
            Node single = pq.poll();
            return new Node(single.freq, single, null);
        }

        // Combine two nodes with lowest frequencies
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            pq.add(new Node(left.freq + right.freq, left, right));
        }

        return pq.peek();
    }

    // Step 3: Generate Huffman Codes
    public static void storeCodes(Node root, String code,
                                  Map<Character, String> huffmanCodes) {

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.ch, code);
            return;
        }

        storeCodes(root.left, code + "0", huffmanCodes);
        storeCodes(root.right, code + "1", huffmanCodes);
    }

    // Step 4: Encode Text
    public static String encodeText(String text,
                                    Map<Character, String> huffmanCodes) {

        StringBuilder encodedText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(ch));
        }

        return encodedText.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        if (text.isEmpty()) {
            System.out.println("Please enter a non-empty string.");
            sc.close();
            return;
        }

        System.out.println("\nOriginal Text: " + text);
        System.out.println("-------------------------");

        Map<Character, Integer> freqMap = buildFrequencyMap(text);

        Node root = buildTree(freqMap);

        Map<Character, String> huffmanCodes = new LinkedHashMap<>();

        storeCodes(root, "", huffmanCodes);

        System.out.println("Generated Binary Codes:");

        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("  '" + entry.getKey() + "' -> " + entry.getValue());
        }

        String encodedText = encodeText(text, huffmanCodes);

        System.out.println("\nCompletely Encoded String:");
        System.out.println(encodedText);

        sc.close();
    }
}
