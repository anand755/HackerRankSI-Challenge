package binary.strings.with.no.adjacent.ones;

import java.io.*;

public class BinaryStringsWithNoAdjacentOnes {
    private static int[] dpTable = new int[(int) 1E5 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        fillDpTableToGetNoAdjacentOnesCount();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int inputLength = Integer.parseInt(reader.readLine());
            int count = dpTable[inputLength];
            writer.write(count + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void fillDpTableToGetNoAdjacentOnesCount() {
        int N = (int) 1E5;
        int M = (int) 1E9 + 7;
        dpTable[0] = 1;
        dpTable[1] = 2;

        for (int i = 2; i <= N; i++) {
            long i_1 = (long) dpTable[i - 1];
            long i_2 = (long) dpTable[i - 2];
            dpTable[i] = (int) ((i_1 + i_2) % M);
        }
    }
}
