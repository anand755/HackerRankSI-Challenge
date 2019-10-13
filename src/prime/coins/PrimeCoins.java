package prime.coins;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        String winnerName = "";
        Set<Integer> moveSet = new HashSet<>();

        //As 1 is always a possible move
        moveSet.add(1);

        for (int i = 2; i <= Math.sqrt(inputPileSize); i++) {

            //Collecting prime number count;
            if (inputPileSize % i == 0) {

                moveSet.add(i);
                moveSet.add(inputPileSize / i);

            }
        }

        int xorVal = 0;
        for (int val : moveSet) {
            xorVal = xorVal ^ val;
        }

        if (xorVal == 0) {
            winnerName = "Banta";
        } else {
            winnerName = "Santa";
        }

        return winnerName;
    }



}
