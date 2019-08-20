package trailing.zeros.easy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrailingZerosEasy {
    //TODO Need to complete
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        int[] numArr = new int[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            numArr[i] = scanner.nextInt();
        }
        Arrays.stream(numArr).forEach(TrailingZerosEasy::printTrailZero);
    }

    static void printTrailZero(int num) {
        long total;

        int startZero = num / 5;


        int div = 100;
        int k = 1;
        long extraZero = 0;
        while (num / div != 0) {

            extraZero = extraZero + ((num / div) * (k));

            div = div * 10;
            k++;
        }
        total = startZero + extraZero;
        System.out.println(total);
    }
}
