package sum.of.array.elements;

import java.util.Arrays;
import java.util.Scanner;

public class SumOfArrayElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();
        long[] resultSum = new long[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int arSize = scanner.nextInt();
            scanner.nextLine();
            String[] arrStr = scanner.nextLine().split(" ");

            long[] arrInt = Arrays.stream(arrStr).mapToLong(Long::parseLong).toArray();

            resultSum[i] = Arrays.stream(arrInt).sum();
        }
        Arrays.stream(resultSum).forEach(System.out::println);
    }
}
