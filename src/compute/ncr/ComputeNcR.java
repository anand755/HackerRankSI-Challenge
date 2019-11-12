package compute.ncr;

import java.io.*;
import java.util.Arrays;

public class ComputeNcR {
    private static int[][] dpTable = new int[2000 + 1][2000 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        fillDPTableToComputeNcR();
        while (testCaseCount-- > 0) {
            int[] NR = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int N = NR[0];
            int R = NR[1];

            int answer = dpTable[N][R];
            writer.write(answer + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void fillDPTableToComputeNcR() {

        int N = 2000;
        int R = 2000;
        int M = (int) 1E9 + 7;


        for (int j = 0; j <= R; j++) {
            dpTable[0][j] = 0;
        }

        for (int i = 0; i <= N; i++) {
            dpTable[i][0] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= R; j++) {

                long i_1__c__j_1 = dpTable[i - 1][j - 1];
                long i_1__c__j = dpTable[i - 1][j];

                dpTable[i][j] = (int) ((i_1__c__j_1 + i_1__c__j) % M);
            }
        }

    }
}
