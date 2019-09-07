package bubble.sort.adhoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BubbleSortAdhoc {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] inputArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int result = getBubbleSortSwapCount(inputArr, arrLength);
            System.out.println(result);
        }
    }

    private static int getBubbleSortSwapCount(int[] inputArr, int arrLength) {

        int swapCount = 0;
        for (int i = 0; i < arrLength - 1; i++) {
            for (int j = 0; j < arrLength - 1 - i; j++) {
                if (inputArr[j] > inputArr[j + 1]) {
                    int temp = inputArr[j];
                    inputArr[j] = inputArr[j + 1];
                    inputArr[j + 1] = temp;
                    swapCount++;
                }
            }
            if (swapCount == 0) {
                return swapCount;
            }
        }
        return swapCount;
    }
}
