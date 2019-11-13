package max.subarray.sum;

import java.io.*;
import java.util.Arrays;

public class MaxSubarraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            SumAndIndex sumAndIndex = getMaxSubArraySum(arrInput, arrLength);
            writer.write(sumAndIndex.sum + " " + sumAndIndex.startIndex + " " + sumAndIndex.lastIndex + "\n");
            writer.flush();
        }
        writer.flush();

    }

    private static SumAndIndex getMaxSubArraySum(int[] arrInput, int N) {

        SumAndIndex[] dpTable = new SumAndIndex[N];
        dpTable[0] = new SumAndIndex(arrInput[0], 0, 0);


        //Populating dp table
        for (int i = 1; i <= N - 1; i++) {

            int currSum = dpTable[i - 1].sum + arrInput[i];
            if (currSum >= arrInput[i]) {
                dpTable[i] = new SumAndIndex(currSum, dpTable[i - 1].startIndex, i);
            } else {
                dpTable[i] = new SumAndIndex(arrInput[i], i, i);
            }

        }


        //Finding max from dp table
        SumAndIndex maxSumIndex = new SumAndIndex(Integer.MIN_VALUE, -1, -1);

        for (int i = 0; i < N; i++) {
            if (dpTable[i].sum > maxSumIndex.sum) {
                maxSumIndex = dpTable[i];
            }
        }


        return maxSumIndex;
    }

    private static class SumAndIndex {
        int sum;
        int startIndex;
        int lastIndex;

        private SumAndIndex(int sum, int startIndex, int lastIndex) {
            this.sum = sum;
            this.startIndex = startIndex;
            this.lastIndex = lastIndex;
        }
    }
}
