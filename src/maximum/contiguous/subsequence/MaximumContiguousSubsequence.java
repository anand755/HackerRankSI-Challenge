package maximum.contiguous.subsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaximumContiguousSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrayInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arrayInput);
            printMaxSubsequenceCount(arrayInput);
        }
    }

    private static void printMaxSubsequenceCount(int[] arrayInput) {
        int maxSubsequenceCount = 1;
        for (int i = 0; i < arrayInput.length - 1; i++) {
            int currSubsCount = 1;

            for (int j = i; j < arrayInput.length - 1; j++) {
                if (arrayInput[j] + 1 == arrayInput[j + 1]) {
                    currSubsCount++;
                } else {
                    break;
                }
            }

            if (currSubsCount > maxSubsequenceCount) {
                maxSubsequenceCount = currSubsCount;
            }
        }
        System.out.println(maxSubsequenceCount);
    }
}
