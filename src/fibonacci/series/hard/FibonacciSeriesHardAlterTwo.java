package fibonacci.series.hard;

import java.io.*;

public class FibonacciSeriesHardAlterTwo {
    private static int N = (int) 1E6;
    private static int mod = (int) 1E9 + 7;
    private static int[][][] matTable = new int[N + 1][2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        computeMatExp();

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            long n = Long.parseLong(reader.readLine());
            int fibonacciNum = getLargeFibNum(n);
            writer.write(fibonacciNum + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getLargeFibNum(long n) {

        int[][] baseMat = new int[2][1];
        baseMat[0][0] = 1;
        baseMat[1][0] = 0;

        int result = 1;
        if (n > 1 && n <= (long) 1E6) {
            result = matMul(matTable[(int) n - 1], baseMat)[0][0];
        } else if (n > (long) 1E6 && n <= (long) 1E12) {
            n = n - 1;
            /*int tempN_1 = (int) n / 2;
            int tempN_2 = (int) (n - tempN_1);*/

            int[][] mat1 = matTable[(int) 1E6];
            int[][] mat2 = matTable[(int) (n / (long) 1E6)];


            int[][] temp_mat = matMulSameDimension(mat1, mat2);
            result = matMul(temp_mat, baseMat)[0][0];
        } else if (n > (long) 1E12) {
            n = n - 1;


            int[][] mat1 = matMulSameDimension(matTable[(int) 1E6], matTable[(int) 1E6]);
            int[][] mat2 = matTable[(int) (n / (long) 1E12)];


            int[][] temp_mat = matMulSameDimension(mat1, mat2);
            result = matMul(temp_mat, baseMat)[0][0];
        }

        return result;

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

    private static int getSmallFibNum(int n) {

        int[][] baseMat = new int[2][1];
        baseMat[0][0] = 1;
        baseMat[1][0] = 0;

        int result = 1;
        if (n >= 2) {
            result = matMul(matTable[n - 1], baseMat)[0][0];
        }

        return result;

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

    private static void computeMatExp() {
        int[][] mat = new int[2][2];
        mat[0][0] = 1;
        mat[0][1] = 1;
        mat[1][0] = 1;
        mat[1][1] = 0;

        int[][] resultMat = mat;
        for (int i = 1; i <= N; i++) {

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
            matTable[i] = resultMat;

        }
    }
}
