package triple.trouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TripleTrouble {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            int[] arr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int result = getNonRepeatedNumber(arr);
            System.out.println(result);
        }
    }

    private static int getNonRepeatedNumber(int[] arr) {
        int nonRepeatedNumber = 0;
        for (int i = 0; i < 31; i++) {
            int bitSum = 0;
            for (int num : arr) {
                if (isCheckBitSet(num, i)) {
                    bitSum++;
                }
            }
            if (bitSum % 3 != 0) {
                nonRepeatedNumber = nonRepeatedNumber + (1 << i);
            }
        }
        return nonRepeatedNumber;
    }

    private static boolean isCheckBitSet(int number, int index) {
        return ((number & (1 << index)) == (1 << index));
    }
}
