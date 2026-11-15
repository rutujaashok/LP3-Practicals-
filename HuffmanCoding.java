/*import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

public class HuffmanCoding {

    // Step 1: Build Frequency Map
    public static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    // Step 2: Build Huffman Tree using basic frequency-based Priority Queue
    public static Node buildTree(Map<Character, Integer> freqMap) {
        // Pure Huffman logic: Always pull two nodes with lowest frequency
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Edge case: Handing a string with only 1 unique character (e.g. "aaaa")
        if (pq.size() == 1) {
            Node single = pq.poll();
            return new Node(single.freq, single, null);
        }

        // Combine two lowest frequency nodes repeatedly
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left.freq + right.freq, left, right));
        }

        return pq.peek();
    }

    // Step 3: Traversal to generate binary codes
    public static void storeCodes(Node root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // If leaf node, save character code
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.ch, code);
            return;
        }

        storeCodes(root.left, code + "0", huffmanCodes);
        storeCodes(root.right, code + "1", huffmanCodes);
    }

    // Step 4: Encode text
    public static String encodeText(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(ch));
        }
        return encodedText.toString();
    }

    public static void main(String[] args) {
        String text = "hello"; 

        System.out.println("Original Text: " + text);
        System.out.println("-------------------------");

        Map<Character, Integer> freqMap = buildFrequencyMap(text);
        Node root = buildTree(freqMap);

        Map<Character, String> huffmanCodes = new LinkedHashMap<>();
        storeCodes(root, "", huffmanCodes);

        System.out.println("Generated Binary Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("  '" + entry.getKey() + "'  ->  " + entry.getValue());
        }

        String encodedText = encodeText(text, huffmanCodes);
        System.out.println("\nCompletely Encoded String:");
        System.out.println(encodedText);
    }
}
*/

/*Output:
PS C:\Users\Rutuja\OneDrive\Documents> javac HuffmanCoding.java
PS C:\Users\Rutuja\OneDrive\Documents> java HuffmanCoding
Original Text: hello
-------------------------
Generated Binary Codes:
  'l'  ->  0
  'e'  ->  10
  'h'  ->  110
  'o'  ->  111

Completely Encoded String:
1101000111 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

public class HuffmanCoding {

    // Step 1: Build Frequency Map
    public static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    // Step 2: Build Huffman Tree using basic frequency-based Priority Queue
    public static Node buildTree(Map<Character, Integer> freqMap) {
        // Pure Huffman logic: Always pull two nodes with lowest frequency
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Edge case: Handing a string with only 1 unique character (e.g. "aaaa")
        if (pq.size() == 1) {
            Node single = pq.poll();
            return new Node(single.freq, single, null);
        }

        // Combine two lowest frequency nodes repeatedly
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left.freq + right.freq, left, right));
        }

        return pq.peek();
    }

    // Step 3: Traversal to generate binary codes
    public static void storeCodes(Node root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // If leaf node, save character code
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.ch, code);
            return;
        }

        storeCodes(root.left, code + "0", huffmanCodes);
        storeCodes(root.right, code + "1", huffmanCodes);
    }

    // Step 4: Encode text
    public static String encodeText(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(ch));
        }
        return encodedText.toString();
    }

    public static void main(String[] args) {
        String text = "hello"; 

        System.out.println("Original Text: " + text);
        System.out.println("-------------------------");

        Map<Character, Integer> freqMap = buildFrequencyMap(text);
        Node root = buildTree(freqMap);

        Map<Character, String> huffmanCodes = new LinkedHashMap<>();
        storeCodes(root, "", huffmanCodes);

        System.out.println("Generated Binary Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("  '" + entry.getKey() + "'  ->  " + entry.getValue());
        }

        String encodedText = encodeText(text, huffmanCodes);
        System.out.println("\nCompletely Encoded String:");
        System.out.println(encodedText);
    }
}