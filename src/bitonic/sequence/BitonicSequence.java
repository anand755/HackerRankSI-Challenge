package bitonic.sequence;

import java.io.*;
import java.util.Arrays;

public class BitonicSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int longestLength = getMaxBitonicSeqLength(arrInput, arrLength);
            writer.write(longestLength + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getMaxBitonicSeqLength(int[] arrInput, int arrLength) {
        int bitonicLength = 0;

        int[] dpTableForward = new int[arrLength];
        int[] dpTableBackward = new int[arrLength];

        //Longest Increasing subsequence in forward direction
        for (int i = 0; i < arrLength; i++) {
            int lis = 0;

            for (int j = 0; j <= i - 1; j++) {
                if (arrInput[j] <= arrInput[i]) {
                    lis = Math.max(lis, dpTableForward[j]);
                }
            }
            dpTableForward[i] = lis + 1;
        }

        //Longest Increasing subsequence in backward direction
        for (int i = arrLength - 1; i >= 0; i--) {
            int lis = 0;

            for (int j = arrLength - 1; j >= i + 1; j--) {
                if (arrInput[j] <= arrInput[i]) {
                    lis = Math.max(lis, dpTableBackward[j]);
                }
            }
            dpTableBackward[i] = lis + 1;
        }


        //Finding bitonic sequence
        for (int i = 0; i < arrLength; i++) {
            int currLength = dpTableForward[i] + dpTableBackward[i] - 1;
            bitonicLength = Math.max(bitonicLength, currLength);
        }

        return bitonicLength;
    }
}
