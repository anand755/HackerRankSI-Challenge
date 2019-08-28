package reverse.bits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReverseBits {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        int[] reserveBits = new int[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {

            int number = Integer.parseInt(reader.readLine());
            reserveBits[i] = calculateReserveBits(number);
        }
        Arrays.stream(reserveBits).forEach(System.out::println);
    }

    private static int calculateReserveBits(int number) {
        int result = 0;
        //need to check here
        for (int i = 0; i < 31; i++) {
            if (checkIsSetBit(number, i)) {
                result = result + (1 << (30 - i));
            }
        }
        return result;
    }

    private static boolean checkIsSetBit(int number, int i) {
        return (((number & (1 << i)) == (1 << i)));

    }
}
