package max.pair.sums.of2.arrays;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxPairSumsOf2ArraysAlter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NK[0];
            int outputNumCount = NK[1];
            int[] arrInputA = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] arrInputB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int[] outPutArr = getMaxPairSum(arrInputA, arrInputB, arrLength, outputNumCount);
            for (int num : outPutArr) {
                writer.write(num + " ");
            }
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();

    }

    private static int[] getMaxPairSum(int[] arrInputA, int[] arrInputB, int arrLength, int outputNumCount) {
        int[] outPutArr = new int[outputNumCount];

        int[] sortedArrA = new int[arrLength];
        int[] sortedArrB = new int[arrLength];

        Arrays.sort(arrInputA);
        Arrays.sort(arrInputB);

        for (int idx = 0; idx < arrLength; idx++) {
            sortedArrA[idx] = arrInputA[arrLength - 1 - idx];
            sortedArrB[idx] = arrInputB[arrLength - 1 - idx];
        }

        int p = 0, q = 0;
        int k = 1;
        outPutArr[0] = sortedArrA[0] + sortedArrB[0];

        while (k < outputNumCount) {
            if (sortedArrA[p + 1] + sortedArrB[q] > sortedArrA[p] + sortedArrB[q + 1]) {
                outPutArr[k] = sortedArrA[p + 1] + sortedArrB[q];
                p++;
            } else if (sortedArrA[p] + sortedArrB[q + 1] > sortedArrA[p + 1] + sortedArrB[q]) {
                outPutArr[k] = sortedArrA[p] + sortedArrB[q + 1];
                q++;
            } else {
                //both equal

                if (sortedArrA[p] < sortedArrB[q]) {
                    outPutArr[k] = sortedArrA[p + 1] + sortedArrB[q];
                    p++;
                } else {
                    outPutArr[k] = sortedArrA[p] + sortedArrB[q + 1];
                    q++;
                }
            }
            k++;
        }


        /*Integer[] completeSumArr = new Integer[arrLength * arrLength];

        int idx = 0;

        for (int a : arrInputA) {
            for (int b : arrInputB) {
                completeSumArr[idx++] = a + b;
            }
        }

        Arrays.sort(completeSumArr, Collections.reverseOrder());

        for (int i = 0; i < outputNumCount; i++) {
            outPutArr[i] = completeSumArr[i];
        }*/


        return outPutArr;
    }
}
