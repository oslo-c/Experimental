package RandomWriter;
// this program generates a random text based on probability of characters following a seed taken from a source file
import java.io.*;
import java.util.*;

public class RandomWriter {
    private static String fileContents;
    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);
        int length = Integer.parseInt(args[1]);
        File source = new File(args[2]);
        File result = new File(args[3]);
        if (!result.exists()) {
            result.createNewFile();
        }

        if (validateArgs(k, length, source, result)) {
            fileContents = readFile(source);
            generateText(k, length, fileContents, result);
        } else {
            System.exit(1);
        }
    }

    public static boolean validateArgs(int k, int length, File source, File result) {
        if (k < 0) {
            System.err.println("Level must be a non-negative integer");
            return false;
        } else if (source.length() < k) { // source.length() returns the number of bytes in the file. A single ASCII character is 1 byte
            System.err.println("Source file must contain more than " + k + " characters");  
            return false;
        } else if (!result.canWrite()) {
            System.err.println("Cannot write to result file");
            return false;
        }
        return true;
    }

    // Reads the entire contents of the source file using a Reader object, appending each character into a StringBuffer object 
    public static String readFile(File source) throws IOException {
        StringBuffer s = new StringBuffer();    // StringBuffer is useful for dynamic string manipulation.
        Reader fileReader = new FileReader(source);  // Reader is useful for reading the file one character at a time, rather than token by token like a Scanner object.
        int character;    // the .read() method returns an int value which can be converted to a char type and appended to the StringBuffer object. 
        while ((character = fileReader.read()) != -1) {    // When the end of the file is reached, the Reader object returns -1, which closes the while loop.
            s.append((char) character);
        }
        fileReader.close();
        return s.toString();
    }

    public static void generateText(int k, int length, String fileContents, File result) throws IOException {
        String seed = generateSeed(k, fileContents);
        while (result.length() < length) { // while the length of the result file is less than the specified length
            char nextChar = choseNextChar(seed, fileContents); // choose the next character based on the current seed
            writeChar(nextChar, result); // write the next character to the result file
            seed = updateSeed(seed, nextChar); // update the seed by removing its first character and appending the next character
        }
    }

    // generates a random seed of the specified level k
    public static String generateSeed(int k, String fileContents) {
        Random random = new Random();
        int randomIndex = random.nextInt((int) (fileContents.length() - k));
        return fileContents.substring(randomIndex, randomIndex + k);
    }

    // chooses the next character based on the current seed
    public static char choseNextChar(String seed, String fileContents) {
        List<Character> probabilities = new ArrayList<>();
        Random random = new Random();
        
        // for each character in the fileContents, if the character follows the seed, add it to the probabilities list
        for (int i = 0; i < fileContents.length() - seed.length() - 1; i++) { 
            if (fileContents.substring(i, i + seed.length()).equals(seed)) {
                probabilities.add(fileContents.charAt(i + seed.length()));
            }
        }
        
        if (probabilities.isEmpty()) { // if no characters follow the seed, generate a new seed and try again
            seed = generateSeed(seed.length(), fileContents);
            return choseNextChar(seed, fileContents);
        }
        
        int randomIndex = random.nextInt(probabilities.size());
        return probabilities.get(randomIndex); // randomly choose a character from the probabilities list
    }

    // writes the next character to the result file
    public static void writeChar(char nextChar, File result) throws IOException {
        Writer fileWriter = new FileWriter(result, true);
        fileWriter.append(nextChar);
        fileWriter.close();
    }

    // updates the seed by removing its first character and appending the next character
    public static String updateSeed(String seed, char nextChar) {
        return seed.substring(1) + nextChar;
    }
}



