package finding.missing.number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Collections;

public class FindingMissingNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        int[] missingNumber = new int[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = new int[arrLength];

            arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            missingNumber[i] = findMissingNumber(arrInput);
        }
        Arrays.stream(missingNumber).forEach(System.out::println);
    }

    private static int findMissingNumber(int[] arrInput) {
        Arrays.sort(arrInput);
        if (arrInput[0] != 1) {
            return 1;

        } else if (arrInput[arrInput.length - 1] != arrInput.length + 1) {
            return arrInput.length + 1;

        } else {
            for (int i = 0; i < arrInput.length - 1; i++) {
                if (arrInput[i + 1] - arrInput[i] > 1) {
                    return arrInput[i] + 1;
                }
            }
        }
        return 0;
    }
}
