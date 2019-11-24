package fibonacci.series.hard;

import java.io.*;

public class FibonacciSeriesHardAlter {
    private static int N = (int) 1E6;
    private static int[] dpTable = new int[N + 1];
    private static int mod = (int) 1E9 + 7;

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


        int[][] mat = new int[2][2];
        mat[0][0] = 1;
        mat[0][1] = 1;
        mat[1][0] = 1;
        mat[1][1] = 0;

        dpTable[0] = 1;
        dpTable[1] = 1;

        int[][] baseMat = new int[2][1];
        baseMat[0][0] = dpTable[1];
        baseMat[1][0] = dpTable[0];


        for (int i = 2; i <= 100; i++) {
            int[][] exp = matExp(mat, i - 1);
            int[][] result = matMul(exp, baseMat);
            dpTable[i] = result[0][0];
        }

    }

    private static int[][] matMul(int[][] powerMat, int[][] baseMat) {

        int[][] resultMat = new int[2][1];
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 1; c++) {
                int val = 0;
                for (int m = 0; m < 2; m++) {
                    long mul = ((long) powerMat[r][m] * (long) baseMat[m][c]) % mod;
                    val = (int) ((val + mul) % mod);
                }
                resultMat[r][c] = val;
            }
        }

        return resultMat;
    }

    private static int[][] matExp(int[][] mat, int k) {
        int[][] resultMat = mat;
        for (int i = 1; i < k; i++) {

            int[][] tempMat = new int[2][2];

            for (int r = 0; r < 2; r++) {

                for (int c = 0; c < 2; c++) {
                    int val = 0;

                    for (int m = 0; m < 2; m++) {
                        long mul = ((long) resultMat[r][m] * (long) mat[m][c]) % mod;
                        val = (int) ((val + mul) % mod);
                    }
                    tempMat[r][c] = val;
                }
            }
            resultMat = tempMat;


        }
        return resultMat;
    }
}
