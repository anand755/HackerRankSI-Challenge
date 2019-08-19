package print.hollow.diamond.pattern;

import java.util.Scanner;

public class DiamondPattern {

    public static void main(String[] args) {
        int t;
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        int[] diamondArr = new int[t];
        for (int i = 0; i < t; i++) {
            diamondArr[i] = scanner.nextInt();
        }
        int caseCount = 1;
        for (int diamond : diamondArr) {
            System.out.println("Case #" + caseCount + ":");
            printDiamond(diamond);
            caseCount++;
        }
    }

    private static void printDiamond(int diamondSize) {

        int topSize = (diamondSize + 1) / 2;
        int bottomSize = diamondSize - topSize;

        for (int i = topSize; i >= 1; i--) {

            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            System.out.print("*");

            int ir = (topSize + 1) - i;
            int midSpace = (ir - 1) * 2 - 1;

            if (midSpace > 0) {
                for (int s = midSpace; s > 0; s--) {
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 1; i <= bottomSize; i++) {

            for (int s = i; s > 0; s--) {
                System.out.print(" ");
            }
            System.out.print("*");

            int ir = bottomSize - i;
            int midSpace = ir * 2 - 1;

            if (midSpace > 0) {
                for (int s = midSpace; s > 0; s--) {
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            System.out.println();

        }
    }
}

