public class Node implements Comparable<Node> {  
    int freq;
    char val;

    Node left;
    Node right;

    Node(int freq, char val) {
        this.freq = freq;
        this.val = val;
        this.left = null;
        this.right = null;
    }

    Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}
