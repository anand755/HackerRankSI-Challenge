package maximum.circular.subarray.sum;

import java.io.*;
import java.util.Arrays;

public class MaximumCircularSubarraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int maxSum = getMaxCircularSubArraySum(arrInput, arrLength);
            writer.write(maxSum + "\n");
            writer.flush();
        }
        writer.flush();

    }

    private static int getMaxCircularSubArraySum(int[] arrInput, int N) {

        SumAndIndex[] dpTable = new SumAndIndex[N];
        dpTable[0] = new SumAndIndex(arrInput[0], 0, 0);


        //Populating dp table
        for (int i = 1; i <= N - 1; i++) {

            int currSum = dpTable[i - 1].sum + arrInput[i];
            if (currSum > arrInput[i]) {
                dpTable[i] = new SumAndIndex(currSum, dpTable[i - 1].startIndex, i);
            } else {
                dpTable[i] = new SumAndIndex(arrInput[i], i, i);
            }

        }


        //Getting Max Value by simply traversing
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (dpTable[i].sum > maxSum) {
                maxSum = dpTable[i].sum;
            }
        }


        //Checking if the Circle makes the max value or not
        int idx = dpTable[N - 1].startIndex;

        int lastVal = dpTable[N - 1].sum;

        int firstSum = 0;


        for (int i = 0; i < idx; i++) {

            firstSum += arrInput[i];
            maxSum = Math.max(maxSum, firstSum + lastVal);

        }

        return maxSum;
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
