package prime.coins;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrimeCoins {
    //TODO: Unable to figure it out. Making xor of possible movements doesn't match with Sample Output0
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
        List<Integer> primeList = getPrimeList(inputPileSize);

        int xorVal = 1;
        for (int movement : primeList) {
            xorVal ^= movement;
        }


        if (xorVal == 0) {
            winnerName = "Banta";
        } else {
            winnerName = "Santa";
        }

        return winnerName;
    }

    private static List<Integer> getPrimeList(int range) {
        List<Integer> prList = new ArrayList<>();

        for (int i = 2; i <= range; i++) {
            prList.add(i);
        }


        for (int num = 2; num <= Math.sqrt(range); num++) {
            int currNum = num;

            if (prList.contains(currNum)) {
                //remove from prime set
                int mul = 2;

                while (currNum * mul <= range) {

                    prList.remove(new Integer(currNum * mul));
                    //prList.remove(currNum * mul);
                    mul++;
                }
            }

        }
        return prList;
    }
}
