package reverse.bits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseBits {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCaseCount; i++) {

            int number = Integer.parseInt(reader.readLine());
            System.out.println(calculateReserveBits(number));
        }
    }

    private static long calculateReserveBits(int number) {
        long result = 0L;
        for (int i = 0; i < 32; i++) {
            if (checkIsSetBit(number, i)) {
                result = result + (1L << (31 - i));
            }
        }
        return result;
    }

    private static boolean checkIsSetBit(int number, int i) {
        return (((number & (1 << i)) == (1 << i)));
    }
}
