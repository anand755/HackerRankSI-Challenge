package finding.missing.number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindingMissingNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        int[] missingNumber = new int[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = new int[arrLength];

            arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            missingNumber[i] = findMissingNumber(arrInput, arrLength);
        }
        Arrays.stream(missingNumber).forEach(System.out::println);
    }

    private static int findMissingNumber(int[] arrInput, int arrLength) {
        int res1 = 0;
        for (int number : arrInput) {
            res1 = res1 ^ number;
        }
        int res2 = 0;
        for (int i = 1; i <= arrLength + 1; i++) {
            res2 = res2 ^ i;
        }
        int missingNumber = res1 ^ res2;

        return missingNumber;
    }
}
