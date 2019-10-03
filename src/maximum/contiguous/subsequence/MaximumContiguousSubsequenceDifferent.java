package maximum.contiguous.subsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaximumContiguousSubsequenceDifferent {
    //I didn't deleted it as this is the solution of slightly modified problem statement
    //If we are not suppose to loose the initial subsequece order i.e can not sort then this is the
    //most optimised solution. Hnce not deleting it.

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrayInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            printMaxSubsequenceCount(arrayInput);
        }
    }

    private static void printMaxSubsequenceCount(int[] arrayInput) {
        int maxSubsequenceCount = 1;
        for (int i = 0; i <= arrayInput.length - 1; i++) {

            for (int j = i; j <= arrayInput.length - 1; j++) {
                int currSubsCount = 1;

                int[] tempArr = Arrays.copyOfRange(arrayInput, i, j + 1);
                Arrays.sort(tempArr);
                for (int k = 0; k < tempArr.length - 1; k++) {
                    if (tempArr[k] + 1 == tempArr[k + 1]) {
                        currSubsCount++;
                    } else {
                        break;
                    }
                }
                maxSubsequenceCount = Math.max(maxSubsequenceCount, currSubsCount);
            }
        }
        System.out.println(maxSubsequenceCount);
    }
}
