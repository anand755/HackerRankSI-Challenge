package the.knapsack.problem;

import java.io.*;
import java.util.Arrays;

public class TheKnapsackProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] SN = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int bagSize = SN[0];
            int itemsCount = SN[1];

            int[] itemSizeArr = new int[itemsCount + 1];
            int[] itemValueArr = new int[itemsCount + 1];

            for (int i = 1; i <= itemsCount; i++) {
                int[] SV = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
                itemSizeArr[i] = SV[0];
                itemValueArr[i] = SV[1];
            }

            int maxValue = getMaxValueFormItems(bagSize, itemsCount, itemSizeArr, itemValueArr);
            writer.write(maxValue + "\n");
            writer.flush();
        }
        writer.flush();

    }

    private static int getMaxValueFormItems(int W, int N, int[] wArr, int[] vArr) {
        int[][] dpTable = new int[N + 1][W + 1];


        for (int j = 1; j <= W; j++) {
            dpTable[0][j] = 0;
        }

        for (int i = 1; i <= N; i++) {
            dpTable[i][0] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
                if (j - wArr[i] < 0) {
                    dpTable[i][j] = dpTable[i - 1][j];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j], (dpTable[i - 1][j - wArr[i]] + vArr[i]));
                }

            }
        }


        return dpTable[N][W];
    }
}
