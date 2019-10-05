package subsequence.sum;

import java.io.*;
import java.util.Arrays;

public class SubsequenceSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NAB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NAB[0];
            int minValue = NAB[1];
            int maxValue = NAB[2];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int count = getSubseSumCount(arrInput, arrLength, minValue, maxValue);
            writer.write(count + "\n");
            //System.out.println(count);

        }
        writer.flush();

    }

    private static int getSubseSumCount(int[] arrInput, int arrLength, int minValue, int maxValue) {
        int count = 0;
        int loopCount = 1 << arrLength;

        for (int i = 0; i < loopCount; i++) {
            int sum = 0;
            for (int j = 0; j < arrLength; j++) {

                if (CB(i, j)) {
                    sum += arrInput[j];
                }
            }
            if ((sum >= minValue) && (sum <= maxValue)) {
                count++;
            }
        }
        return count;
    }

    private static boolean CB(int num, int pos) {
        return ((num & (1 << pos)) == (1 << pos));
    }
}
