package product.of2.matrices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductOf2Matrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        List<int[][]> matList = new ArrayList<>();

        for (int i = 0; i < testCaseCount * 2; i++) {
            int rowCount = scanner.nextInt();
            int colCount = scanner.nextInt();

            int[][] mat = new int[rowCount][colCount];

            for (int j = 0; j < rowCount; j++) {
                for (int k = 0; k < colCount; k++) {
                    mat[j][k] = scanner.nextInt();
                }
            }
            matList.add(mat);
        }

        for (int i = 0; i < matList.size(); i = i + 2) {
            doProductOfMatrices(matList.get(i), matList.get(i + 1));
        }


    }

    private static void doProductOfMatrices(int[][] mat1, int[][] mat2) {

        int rowMat1 = mat1.length;
        int colMat1 = mat1[0].length;

        //colMat1=rowMat2
        int rowMat2 = mat2.length;
        int colMat2 = mat2[0].length;

        int[][] resultMat = new int[rowMat1][colMat2];

        for (int i = 0; i < rowMat1; i++) {
            for (int j = 0; j < colMat2; j++) {

                int mul = 0;
                for (int k = 0; k < colMat1; k++) {
                    mul = mul + mat1[i][k] * mat2[k][j];
                }
                resultMat[i][j] = mul;
            }
        }


        for (int i = 0; i < resultMat.length; i++) {
            for (int j : resultMat[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
