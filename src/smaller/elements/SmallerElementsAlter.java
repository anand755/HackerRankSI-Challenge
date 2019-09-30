package smaller.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SmallerElementsAlter {
    //TODO Working code but need better solution. This is brute force solution
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            countSmallerElements(arrInput, arrSize);
        }
    }

    private static void countSmallerElements(int[] arrInput, int arrSize) {
        int count = 0;
        for (int i = 0; i < arrSize - 1; i++) {
            int currentElement = arrInput[i];
            for (int j = i + 1; j < arrSize; j++) {
                if (arrInput[j] < currentElement) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
