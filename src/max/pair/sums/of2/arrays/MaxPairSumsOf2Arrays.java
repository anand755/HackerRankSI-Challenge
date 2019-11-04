package max.pair.sums.of2.arrays;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class MaxPairSumsOf2Arrays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NK[0];
            int outputElementsCount = NK[1];
            int[] arrInputA = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] arrInputB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int[] outPutArr = getMaxPairSum(arrInputA, arrInputB, arrLength, outputElementsCount);
            for (int num : outPutArr) {
                writer.write(num + " ");
            }
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();

    }

    private static int[] getMaxPairSum(int[] arrInputA, int[] arrInputB, int arrLength, int outputElementsCount) {

        int[] outPutArr = new int[outputElementsCount];

        Integer[] completeSumArr = new Integer[arrLength * arrLength];

        int idx = 0;

        for (int a : arrInputA) {
            for (int b : arrInputB) {
                completeSumArr[idx++] = a + b;
            }
        }

        Arrays.sort(completeSumArr, Collections.reverseOrder());

        for (int i = 0; i < outputElementsCount; i++) {
            outPutArr[i] = completeSumArr[i];
        }


        return outPutArr;
    }
}
