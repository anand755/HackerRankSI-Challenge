package game.of.letters;

import java.io.*;

public class GameOfLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            String inputString = reader.readLine().trim();
            String winnerName = getWinnerName(inputString);
            writer.write(winnerName + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static String getWinnerName(String inputString) {

        String winnerName;

        int xorVal = 0;
        int[] charCount = new int[26];

        for (char ch : inputString.toCharArray()) {
            charCount[ch-(int)'a']++;
        }

        for (int count : charCount) {
            xorVal = xorVal ^ count;
        }

        if (xorVal == 0) {
            winnerName = "Banta";
        } else {
            winnerName = "Santa";
        }
        return winnerName;
    }
}
