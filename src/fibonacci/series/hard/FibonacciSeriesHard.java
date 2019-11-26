package fibonacci.series.hard;

import java.io.*;

public class FibonacciSeriesHard {
    private static int mod = (int) 1E9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            long n = Long.parseLong(reader.readLine());
            int fibonacciNum = getNthFibNum(n);
            writer.write(fibonacciNum + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getNthFibNum(long n) {

        int[][] baseMat = new int[2][1];
        baseMat[0][0] = 1;
        baseMat[1][0] = 0;

        int[][] fibMat = new int[2][2];
        fibMat[0][0] = 1;
        fibMat[0][1] = 1;
        fibMat[1][0] = 1;
        fibMat[1][1] = 0;

        if (n <= 1) {
            return 1;
        }
        int[][] poweredMat = computePow(fibMat, n);

        int result = matMulDifferentDimension(poweredMat, baseMat)[0][0];

        return result;

    }

    private static int[][] computePow(int[][] mat, long pow) {
        if (pow == 0) {
            int[][] identityMat = new int[2][2];
            identityMat[0][0] = 1;
            identityMat[0][1] = 0;
            identityMat[1][0] = 0;
            identityMat[1][1] = 1;

            return identityMat;
        }


        int[][] resultMat = computePow(mat, pow / 2);

        resultMat = matMulSameDimension(resultMat, resultMat);

        if (pow % 2 == 1) {
            resultMat = matMulSameDimension(resultMat, mat);
        }

        return resultMat;
    }

    private static int[][] matMulSameDimension(int[][] mat1, int[][] mat2) {
        int[][] resultMat = new int[2][2];

        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 2; c++) {
                int val = 0;
                for (int m = 0; m < 2; m++) {
                    long mul = (((long) mat1[r][m] * (long) mat2[m][c]) % mod);
                    val = (int) ((val + mul) % mod);
                }
                resultMat[r][c] = val;
            }
        }
        return resultMat;
    }


    private static int[][] matMulDifferentDimension(int[][] powerMat, int[][] baseMat) {

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
}
