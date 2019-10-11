package game.of.letters;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GameOfLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            String inputString = reader.readLine().trim();
            String winnerName = getWinnerName(inputString);
            writer.write(winnerName);
            writer.flush();
        }
        //writer.flush();
    }

    private static String getWinnerName(String inputString) {
        Map<Character, Integer> charMap = new HashMap<>();

        for (char ch : inputString.toCharArray()) {
            int freq = charMap.getOrDefault(ch, 0);
            charMap.put(ch, freq + 1);
        }


        return null;
    }
}
