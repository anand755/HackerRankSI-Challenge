package trailing.zeros.easy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrailingZerosEasy {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        long[] numArr = new long[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            numArr[i] = scanner.nextLong();
        }
        Arrays.stream(numArr).forEach(TrailingZerosEasy::printTrailZero);
    }

    static void printTrailZero(long num) {
        long div = 5;

        long totalZeroCount = 0;
        while (num / div != 0) {
            totalZeroCount = totalZeroCount + num / div;
            div = div * 5;
        }
        System.out.println(totalZeroCount);
    }
}
