import java.util.*;

public class HuffmanTree {
    private Node root;
    private Map<Character, String> codeMap;
    private String encodedString;

    public HuffmanTree(String text) {
        Map<Character, Integer> frequencies = countFrequencies(text);
        this.root = buildTree(frequencies);
        this.codeMap = makeCodeMap(root);
        this.encodedString = encodeText(text, codeMap);
    }

    private Map<Character, Integer> countFrequencies(String text) {
        Map<Character, Integer> frequencies = new HashMap<Character, Integer>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        return frequencies;
    }

    private Node buildTree(Map<Character, Integer> frequencies) {
        Queue<Node> nodes = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            nodes.add(new Node(entry.getValue(), entry.getKey()));
        }

        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            nodes.add(new Node(left.freq + right.freq, left, right));
        }
        return nodes.poll();
    }

    public Map<Character, String> getCodeMap() {
        return codeMap;
    }
    
    public String getEncoded() {
        return encodedString;
    }

    private Map<Character, String> makeCodeMap(Node n) {
        Map<Character, String> codeMap = new HashMap<>();
        makeCodeMap(n, "", codeMap);
        return codeMap;
    }

    private void makeCodeMap(Node n, String code, Map<Character, String> codeMap) {
        if (n.left == null && n.right == null) {
            codeMap.put(n.val, code);
        } else {
            if (n.left != null) {
                makeCodeMap(n.left, code + "0", codeMap);
            }
            if (n.right != null) {
                makeCodeMap(n.right, code + "1", codeMap);
            }
        }
    }

    public String decode(String encoded) {
        return decode(root, encoded);
    }

    private String decode(Node n, String encoded) {
        StringBuilder result = new StringBuilder();
        Node current = root;
        for (int i = 0; i < encoded.length(); i++) {
            char c = encoded.charAt(i);
            if (c == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                result.append(current.val);
                current = root;
            }
        }
        return result.toString();
    }

    private String encodeText(String text, Map<Character, String> codeMap) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codeMap.get(c));
        }
        return encoded.toString();
    }
}
