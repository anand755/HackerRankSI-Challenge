package two.set.bits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoSetBits {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            long n = Long.valueOf(reader.readLine());

            int lowerSetBitIndex = -1, higherSetBitIndex = 1;

            for (long i = 0; i < n; i++) {
                lowerSetBitIndex++;
                if (lowerSetBitIndex == higherSetBitIndex) {
                    higherSetBitIndex++;
                    lowerSetBitIndex = 0;
                }
            }
            int M = (int) 1e9 + 7;
            long result = (((1 << lowerSetBitIndex) % M) + ((1 << higherSetBitIndex) % M)) % M;
            System.out.println(result);
        }

    }
}
