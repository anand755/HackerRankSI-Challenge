package non.adjacent.set.bits;

import java.io.*;

public class NonAdjacentSetBits {
    private static int N = (int) 1E6;
    private static int[] dpTable = new int[N + 1];


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        fillDpTableToGetNoAdjacentOnesCount();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int inputLength = Integer.parseInt(reader.readLine());
            long count = dpTable[inputLength];
            writer.write(count + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void fillDpTableToGetNoAdjacentOnesCount() {
        int M = (int) 1E9 + 7;
        dpTable[0] = 0;
        dpTable[1] = 2;
        dpTable[2] = 4;
        dpTable[3] = 7;

        for (int i = 4; i <= N; i++) {
            long dp_val = ((long) dpTable[i - 1] + (long) dpTable[i - 2] + (long) dpTable[i - 3]);

            dpTable[i] = (int) (dp_val % M);
        }

    }
}
