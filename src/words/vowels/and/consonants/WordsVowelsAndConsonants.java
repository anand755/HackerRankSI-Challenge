package words.vowels.and.consonants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WordsVowelsAndConsonants {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String line = reader.readLine().trim();
            printResults(line);
        }

    }

    private static void printResults(String line) {

        String[] words = line.split("\\s");

        int wordCount = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;
            }
        }

        ArrayList<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int vowelCount = 0;
        int constCount = 0;

        line = line.replaceAll("\\s", "");
        for (char ch : line.toLowerCase().toCharArray()) {
            if (vowels.contains(ch)) {
                vowelCount++;
            } else {
                constCount++;
            }
        }
        System.out.println(wordCount + " " + vowelCount + " " + constCount);
    }
}
