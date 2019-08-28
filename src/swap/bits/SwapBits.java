package swap.bits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class SwapBits {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        int[] swappedBits = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int number = Integer.parseInt(reader.readLine());
            swappedBits[i] = swapBit(number);
        }
        Arrays.stream(swappedBits).forEach(System.out::println);

    }

    private static int swapBit(int number) {
        int result = 0;
        for (int i = 0; i < 31; i += 2) {
            if (isSetBit(number, i)) {
                result = result + (1 << (i + 1));
            }
            if (isSetBit(number, i + 1)) {
                result = result + (1 << i);
            }
        }
        return result;
    }

    private static boolean isSetBit(int number, int position) {
        return ((number & (1 << position)) != 0);
    }
}
