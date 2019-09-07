package insertion.sort.adhoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InsertionSortAdhoc {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            insertionSort(arrInput, arrLength);
            System.out.println();
        }

    }

    private static void insertionSort(int[] arrInput, int arrLength) {
        for (int i = 1; i < arrLength; i++) {
            int temp = arrInput[i];

            int j = i - 1;
            while ((j >= 0) && (arrInput[j] > temp)) {
                arrInput[j + 1] = arrInput[j];
                j--;
            }
            arrInput[j + 1] = temp;
            System.out.print(j + 1 + " ");
        }
    }
}
