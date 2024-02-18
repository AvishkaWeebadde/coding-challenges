import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CustomWordCount {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java CustomWC <file> [-l] [-w] [-c]");
            System.exit(1);
        }

        String fileName = args[0];
        boolean countLines = containsFlag(args, "-l");
        boolean countWords = containsFlag(args, "-w");
        boolean countCharacters = containsFlag(args, "-c");

        processFile(fileName, countLines, countWords, countCharacters);
    }

    /**
     * Process the file and print the results
     * read the file and count the lines, words, and characters
     * then print the results
     * @param fileName file to process
     * @param countLines number of lines
     * @param countWords number of words
     * @param countCharacters number of characters
     */
    private static void processFile(String fileName, boolean countLines, boolean countWords, boolean countCharacters) {
        // read the file and count the lines, words, and characters
        // then print the results

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int lines = 0;
            int words = 0;
            int characters = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                words += countWords(line);
                characters += line.length();
            }

            if (countLines) {
                System.out.println("Lines: " + lines);
            }
            if (countWords) {
                System.out.println("Words: " + words);
            }
            if (countCharacters) {
                System.out.println("Characters: " + characters);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Count the number of words in the given line
     * @param line line to count the words
     * @return number of words in the line
     */
    private static int countWords(String line) {
        String[] words = line.split("\\s+");
        return words.length;
    }

    /**
     * Check if the given array contains the given string
     * @param args array of strings
     * @param s string to check
     * @return true if the array contains the string, false otherwise
     */
    private static boolean containsFlag(String[] args, String s) {
        for (String arg : args) {
            if (arg.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
