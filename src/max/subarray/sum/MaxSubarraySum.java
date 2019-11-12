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
            writer.write(sumAndIndex.sum + " " + sumAndIndex.firstIndex + " " + sumAndIndex.lastIndex + "\n");
            writer.flush();
        }
        writer.flush();

    }

    private static SumAndIndex getMaxSubArraySum(int[] arrInput, int N) {
        int maxVal = 0;
        int[] dpTable = new int[N];
        dpTable[0] = Math.max(arrInput[0], 0);


        SumAndIndex[] dpTableObject = new SumAndIndex[N];
        dpTableObject[0] = new SumAndIndex(arrInput[0], 0, 0);
        //below commented was my logic.
        /*for (int i = 1; i <= N - 1; i++) {
            dpTable[i] = Math.max(dpTable[i - 1] + arrInput[i], 0);
        }*/


        for (int i = 1; i <= N - 1; i++) {
            dpTable[i] = Math.max(dpTable[i - 1] + arrInput[i], arrInput[i]);
        }


        int firstIndex = 0;
        int lastIndex = 0;
        for (int i = 0; i < N; i++) {

            if (dpTable[i] > maxVal) {
                maxVal = dpTable[i];
                firstIndex = i;
                lastIndex = i;
            }
        }

        for (int index = lastIndex; index >= 0; index--) {
            if (dpTable[index] >= arrInput[index]) {
                firstIndex = index;
            }
        }

        /*if (firstIndex == 0 && arrInput[0] < 0) {
            firstIndex++;
        }*/

        SumAndIndex sumAndIndex = new SumAndIndex(maxVal, firstIndex, lastIndex);

        return sumAndIndex;
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
