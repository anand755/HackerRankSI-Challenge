package diagonal.traversal.of.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DiagonalTraversalOfMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        List<int[][]> matList = new ArrayList<>();

        for (int i = 0; i < testCaseCount; i++) {
            int matSize = scanner.nextInt();
            scanner.nextLine();
            int[][] mat = new int[matSize][matSize];

            for (int j = 0; j < matSize; j++) {
                for (int k = 0; k < matSize; k++) {
                    mat[j][k] = scanner.nextInt();
                }
            }

            matList.add(mat);

        }

        matList.forEach(DiagonalTraversalOfMatrix::printSum);
        //printSum(matList.get(0));
    }

    private static void printSum(int[][] mat) {
        int matSize = mat.length;
        int[] sumArr = new int[(2 * matSize) - 1];

        List<Integer> loopList = new ArrayList<>();

        int resultIndex = 0;

        for (int i = matSize; i >= 1; i--) {
            loopList.add(i);

            int startIndex = loopList.get(loopList.size() - 1);
            int lastIndex = loopList.get(0);

            int k = 0;
            int sum = 0;

            for (int j = startIndex; j <= lastIndex; j++) {
                sum = sum + mat[k][j - 1];
                k++;
            }
            sumArr[resultIndex] = sum;
            resultIndex++;
        }

        for (int i = 1; i<matSize; i++){
            int sum = 0;
            int k =0;
            for (int j = i; j<matSize; j++){
                sum = sum+mat[j][k];
                k++;
            }
            sumArr[resultIndex]=sum;
            resultIndex++;
        }

        Arrays.stream(sumArr).forEach(i -> {
            System.out.print(i + " ");
        });
        System.out.println();
    }
}
