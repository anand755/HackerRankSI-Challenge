package print.right.angle.triangle.pattern;

import java.util.Scanner;

public class RightAngleTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        scanner.nextLine();

        int[] triangleArr = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            triangleArr[i] = scanner.nextInt();
        }


        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("Case #" + (i + 1)+":");
            printTriangle(triangleArr[i]);
        }
    }

    private static void printTriangle(int n) {
        for (int i = 1; i <= n; i++) {


            for (int k = 1; k <= n - i; k++) {
                System.out.print(" ");
            }
            for (int l = 1; l <= i; l++) {
                System.out.print("*");
            }
            System.out.println();

        }
    }
}
