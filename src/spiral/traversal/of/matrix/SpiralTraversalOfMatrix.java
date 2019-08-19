package spiral.traversal.of.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpiralTraversalOfMatrix {
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
        matList.forEach(SpiralTraversalOfMatrix::spiralTraverse);
    }

    private static void spiralTraverse(int[][] mat) {
        int layerCount = 0;
        if (mat.length % 2 == 1) {
            layerCount = mat.length / 2 + 1;
        } else {
            layerCount = mat.length;
        }

        for (int layer = 0; layer < layerCount; layer++) {
            int startN = layer;
            int endN = (mat.length - 1) - layer;

            //Top
            for (int i = startN; i <= (endN - 1); i++) {
                System.out.print(mat[startN][i] + " ");
            }
            //Right
            for (int i = startN; i <= (endN - 1); i++) {
                System.out.print(mat[i][endN] + " ");
            }
            //Bottom
            for (int i = endN; i >= (startN + 1); i--) {
                System.out.print(mat[endN][i] + " ");
            }
            //Left
            for (int i = endN; i >= (startN + 1); i--) {
                System.out.print(mat[i][startN] + " ");
            }

            if (startN == endN) {
                System.out.print(mat[startN][endN]);
            }
        }
        System.out.println();
    }
}
