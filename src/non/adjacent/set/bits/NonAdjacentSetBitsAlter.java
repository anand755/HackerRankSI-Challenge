package non.adjacent.set.bits;

import java.io.*;

public class NonAdjacentSetBitsAlter {
    private static int N = 30;
    private static long[] dpTableNoTwoSetBits = new long[N + 1];
    private static long[] dpTableThreeSetBits = new long[N + 1];

    private static long[] answerArr = new long[N + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        fillDpTableToGetNoAdjacentOnesCount();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int inputLength = Integer.parseInt(reader.readLine());
            long count = answerArr[inputLength];
            writer.write(count + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void fillDpTableToGetNoAdjacentOnesCount() {
        //int M = (int) 1E9 + 7;
        /*dpTableNoTwoSetBits[0] = 1L;
        dpTableNoTwoSetBits[1] = 2L;

        for (int i = 2; i <= N; i++) {
            long i_1 = dpTableNoTwoSetBits[i - 1];
            long i_2 = dpTableNoTwoSetBits[i - 2];
            dpTableNoTwoSetBits[i] = (int) ((i_1 + i_2));
        }

        //dpTableThreeSetBits[1] = 0L;
        //dpTableThreeSetBits[2] = 0L;

        answerArr[1] = 2L;
        answerArr[2] = 4L;

        for (long i = 3; i <= N; i++) {
            answerArr[(int) i] = (1L << (i - 1L)) + dpTableNoTwoSetBits[(int) i - 1];
            //answerArr[(int) i] = (1L << i) - dpTableThreeSetBits[(int) i];
        }*/

        int[] dpTable = new int[30];


        dpTable[0] = 0;
        dpTable[1] = 0;
        dpTable[2] = 0;
        dpTable[3] = 1;

        for (int i = 4; i < 21; i++) {
            dpTable[i] = 2 * dpTable[i - 1] + (1 << (i - 4));
        }

        for (int i = 0; i < 21; i++) {
            answerArr[i] = (1 << i) - dpTable[i];
        }

    }
}
