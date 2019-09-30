package sum.of.subarrays;

import java.io.*;
import java.util.Arrays;

public class SumOfSubarrays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrayLength = Integer.parseInt(reader.readLine());
        long[] arrayInput = Arrays.stream(reader.readLine().split("\\s")).mapToLong(Long::parseLong).toArray();
        long[] cumilativeSumArray = makeCumilativeSum(arrayInput, arrayLength);

        int queryCount = Integer.parseInt(reader.readLine());
        while (queryCount-- > 0) {
            int[] startAndCloseIndex = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int startIndex = startAndCloseIndex[0];
            int closeIndex = startAndCloseIndex[1];
            long sum;
            if (startIndex == 0) {
                sum = cumilativeSumArray[closeIndex];
            } else {
                sum = cumilativeSumArray[closeIndex] - cumilativeSumArray[startIndex - 1];
            }
            writer.write(sum + "\n");
            writer.flush();
        }
    }

    private static long[] makeCumilativeSum(long[] arrayInput, int arrayLength) {
        long[] sumArr = new long[arrayLength];

        sumArr[0] = arrayInput[0];

        for (int i = 1; i < arrayLength; i++) {
            sumArr[i] = sumArr[i - 1] + arrayInput[i];
        }

        return sumArr;
    }
}
