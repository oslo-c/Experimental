import java.io.*;
import java.util.*;

public class boggleBot {
    Set<String> dictionary;
    Set<String> validWords = new HashSet<>();
    coordinates[][] board;
    public static void main(String[] args) throws FileNotFoundException {
        boggleBot b = new boggleBot();
        b.dictionary = importDictionary();
        b.board = generateBoard();
        System.out.print("Found: ");
        b.findAllWords();
        System.out.println();
    }

    public static Set<String> importDictionary() throws FileNotFoundException {
        Set<String> dictionary = new HashSet<>();
        Scanner s = new Scanner(new File("Boggle/boggleDictionary.txt"));
        while (s.hasNext()) {
            dictionary.add(s.nextLine().toUpperCase());
        }
        s.close();
        return dictionary;
    }

    public static coordinates[][] generateBoard() { // generates a random boggle board
        coordinates[][] board = new coordinates[4][4];
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new coordinates(i, j, (char) (r.nextInt(26) + 'A'), board);
            }
        }
        printBoard(board);
        return board;
    }
    public static void printBoard(coordinates[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(" | " + board[i][j].getLetter()); // print the board so the user can see it
            }
            System.out.println(" |");
        }
    }

    private void findAllWords() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                wordSearch(board[i][j], String.valueOf(board[i][j].getLetter()), dictionary, new ArrayList<>());
            }
        }
    }

    private void wordSearch(coordinates current, String seed, Set<String> dictionary, List<coordinates> visited) {
        List<coordinates> neighbors = current.getNeighbors(board);
        String currentSeed = seed;

        validateWord(currentSeed);
        visited.add(current);

        for (coordinates neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                List<coordinates> newVisited = new ArrayList<>(visited);
                wordSearch(neighbor, currentSeed + String.valueOf(neighbor.getLetter()), dictionary, newVisited);
            }
        }
    }

    private void validateWord(String word) {
        if (dictionary.contains(word) && word.length() > 1) {
            validWords.add(word);
            System.out.print(word + " ");
        }
    }
}
