import java.util.*;

public class HuffmanClient {
    public static void main(String[] args) {
        String input = getInput();  
        HuffmanTree tree = new HuffmanTree(input);

        

        System.out.println("\n\nInput: " + input);
        System.out.println("Code Map: " + tree.getCodeMap());
        System.out.println("\nCompressed Representation: " + tree.getEncoded());
        
        System.out.println("Binary Representation: " + inputToBinary(input));
        System.out.println("\nDecoded: " + tree.decode(tree.getEncoded()));
    }

    public static String getInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("String to compress: ");
        String text = input.nextLine();
        input.close();
        return text;
    }

    public static String inputToBinary(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(Integer.toBinaryString(c));
        }
        return result.toString();
    }
}
