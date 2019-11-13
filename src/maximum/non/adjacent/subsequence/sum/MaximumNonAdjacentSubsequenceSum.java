package maximum.non.adjacent.subsequence.sum;

import java.io.*;
import java.util.Arrays;

public class MaximumNonAdjacentSubsequenceSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int sum = getMaxNonAdjacentSubseqSum(arrInput, arrLength);
            writer.write(sum + "\n");
            writer.flush();
        }
        writer.flush();
    }

    private static int getMaxNonAdjacentSubseqSum(int[] arrInput, int N) {
        int[] dpTable = new int[N];


        dpTable[0] = arrInput[0];

        if (N == 1) {
            return arrInput[0];
        }
        dpTable[1] = Math.max(arrInput[0], arrInput[1]);

        int ans = Math.max(arrInput[0], arrInput[1]);

        for (int i = 2; i <= N - 1; i++) {

            int currMaxVal = Math.max(dpTable[i - 2] + arrInput[i], Math.max(arrInput[i], dpTable[i - 1]));

            dpTable[i] = currMaxVal;
            ans = Math.max(ans, currMaxVal);
        }

        return ans;
    }
}
