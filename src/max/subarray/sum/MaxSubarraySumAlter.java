package max.subarray.sum;

import java.io.*;
import java.util.Arrays;

public class MaxSubarraySumAlter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            SumAndIndex sumAndIndex = getMaxSubArraySum(arrInput, arrLength);
            writer.write(sumAndIndex.sum + " " + sumAndIndex.firstIndex + " " + sumAndIndex.lastIndex + "\n");
            writer.flush();
        }
        writer.flush();

    }

    private static SumAndIndex getMaxSubArraySum(int[] arrInput, int N) {

        int[] dpTable = new int[N];
        dpTable[0] = arrInput[0];


        //Populating dp table
        for (int i = 1; i <= N - 1; i++) {
            dpTable[i] = Math.max(dpTable[i - 1] + arrInput[i], arrInput[i]);
        }


        //Finding max from dp table
        int maxVal = Integer.MIN_VALUE;
        int endIndex = 0;
        for (int i = 0; i < N; i++) {
            if (dpTable[i] > maxVal) {
                maxVal = dpTable[i];
                endIndex = i;
            }
        }

        //Finding the startIndex by backtracking

        int startIndex = endIndex;
        int reqVal = maxVal;


        for (int i = endIndex; i >= 1; i--) {

            if (reqVal - arrInput[i] >= 0) {
                startIndex = i;
                reqVal -= arrInput[i];

            } else {
                break;
            }
        }


        //If the startIndex came all the way to 1 and
        // dpTable[0] (base condition) is also positive then startIndex will be 0
        if (startIndex == 1 && arrInput[0] >= 0) {
            startIndex = 0;
        }

        //If Maxval is negative then startIndex and endIndex must be same
        // because maxval negative means there is no positive value in the array
        //So adding any negative to value in a negative sum can never be tne ans
        if (maxVal < 0) {
            startIndex = endIndex;
        }


        return new SumAndIndex(maxVal, startIndex, endIndex);
    }

    private static class SumAndIndex {
        int sum;
        int firstIndex;
        int lastIndex;

        private SumAndIndex(int sum, int firstIndex, int lastIndex) {
            this.sum = sum;
            this.firstIndex = firstIndex;
            this.lastIndex = lastIndex;
        }
    }
}
