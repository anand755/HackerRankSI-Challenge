package fibonacci.series.hard;

import java.io.*;

public class FibonacciSeriesHard {
    private static int N = (int) 1E6;
    private static int[] dpTable = new int[N + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        fillFibonacciSeries();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int N = Integer.parseInt(reader.readLine());
            int fibonacciNum = dpTable[N];
            writer.write(fibonacciNum + "\n");
            writer.flush();
        }
        writer.flush();
    }

    private static void fillFibonacciSeries() {

        int M = (int) 1E9 + 7;
        dpTable[0] = 1;
        dpTable[1] = 1;

        for (int i = 2; i <= N; i++) {
            int val = (int) (((long) dpTable[i - 1] + (long) dpTable[i - 2]) % M);
            dpTable[i] = val;
        }
    }
}
