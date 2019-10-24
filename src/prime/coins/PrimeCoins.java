package prime.coins;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrimeCoins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            int inputPileSize = Integer.parseInt(reader.readLine());
            String winnerName = getWinnerName(inputPileSize);

            writer.write(winnerName + "\n");
            writer.flush();
        }
        //writer.flush();
    }
    private static String getWinnerName(int inputPileSize) {
        //As per the problem statement possible moves are
        //1 2 3 4 5 7 8 9 11 13 16 etc....

        //If we prepare the game table then it looks like below

        //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
        //W W W W W L W W W W  W  L  W  W  W  W  W  L
        // As per the pattern loosing position repeats after 6 number
        if (inputPileSize % 6 == 0) {
            return "Banta";
        } else {
            return "Santa";
        }
    }
}
