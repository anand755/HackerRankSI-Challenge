package selection.sort.adhoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SelectionSortAdhoc {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            selectionSort(arrInput, arrLength);
            System.out.println();
        }
    }

    private static void selectionSort(int[] arrInput, int arrLength) {

        for (int i = arrLength - 1; i > 0; i--) {
            int maxValueIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arrInput[j] >= arrInput[maxValueIndex]) {
                    maxValueIndex = j;
                }
            }
            if (maxValueIndex != i) {
                //swapping value
                int temp = arrInput[maxValueIndex];
                arrInput[maxValueIndex] = arrInput[i];
                arrInput[i] = temp;
            }
            System.out.print(maxValueIndex + " ");
        }
    }
}
