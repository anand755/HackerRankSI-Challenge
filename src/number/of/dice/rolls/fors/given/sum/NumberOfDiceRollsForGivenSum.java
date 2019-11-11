package number.of.dice.rolls.fors.given.sum;

import java.io.*;

public class NumberOfDiceRollsForGivenSum {
    private static int[] dpTable = new int[(int) 1E5 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        fillDpTableOfWaysCountToGetSum();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int requiredSum = Integer.parseInt(reader.readLine());

            int totalWays = dpTable[requiredSum];
            writer.write(totalWays + "\n");
            writer.flush();
        }
        //writer.flush();
    }


    private static void fillDpTableOfWaysCountToGetSum() {
        int N = (int) 1E5;
        int M = (int) 1E9 + 7;

        dpTable[0] = 1;

        for (int i = 1; i <= N; i++) {

            long val = 0L;
            for (int j = 1; j <= 6 && (i - j >= 0); j++) {
                val = (val + dpTable[(i - j)]) % M;
            }

            dpTable[i] = (int) val;
        }
    }
}
