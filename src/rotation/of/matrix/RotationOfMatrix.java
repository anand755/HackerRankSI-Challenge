package rotation.of.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RotationOfMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        List<int[][]> matList = new ArrayList<>();

        for (int i = 0; i < testCaseCount; i++) {
            int matSize = scanner.nextInt();

            int[][] mat = new int[matSize][matSize];

            for (int j = 0; j < matSize; j++) {
                for (int k = 0; k < matSize; k++) {
                    mat[j][k] = scanner.nextInt();
                }
            }
            matList.add(mat);
        }

        int count = 1;
        for (int[][] mat : matList) {
            System.out.println("Test Case #" + count + ":");
            rotateMatrix(mat);
            count++;
        }

    }

    private static void rotateMatrix(int[][] mat) {
        int[][] reslutMat = new int[mat.length][mat.length];


        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                reslutMat[i][j] = mat[mat.length - 1 - j][i];
            }
        }

        for (int[] line : reslutMat) {
            Arrays.stream(line).forEach(num -> System.out.print(num + " "));
            System.out.println();
        }

    }
}
