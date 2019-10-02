package two.set.bits;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TwoSetBits {
    private static int M = (int) 1e9 + 7;
    private static int maxModVal = (1 << 30) % M;
    private static Map<Integer, Long> powerHash;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        computePowerHash();

        int lowerSetBitIndex, higherSetBitIndex;
        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            long pos = Long.valueOf(reader.readLine());


            //Using Quadratic Equation formula for ax2 + bx + c = 0 to compute x
            double x = (Math.sqrt(1 + 4 * 2 * pos) - 1) / 2;
            if (x == Math.floor(x) && !Double.isInfinite(x)) {
                higherSetBitIndex = (int) x;
                lowerSetBitIndex = higherSetBitIndex - 1;
            } else {
                higherSetBitIndex = (int) x + 1;
                long y = (int) x;
                lowerSetBitIndex = (int) ((pos - (y * (y + 1) / 2)) - 1);
            }

            int higherBitDiv = higherSetBitIndex / 30;
            int higherBitRem = higherSetBitIndex % 30;
            long highVal = (powerHash.get(higherBitDiv) * (1L << higherBitRem)) % M;

            int lowerBitDiv = lowerSetBitIndex / 30;
            int lowerBitRem = lowerSetBitIndex % 30;
            long lowVal = (powerHash.get(lowerBitDiv) * (1L << lowerBitRem)) % M;

            long result = (highVal + lowVal) % M;
            writer.write(result + "\n");
            writer.flush();
        }
    }

    private static void computePowerHash() {
        //This hashmap is to compute ((2^30)%M)^i
        powerHash = new HashMap<>();
        powerHash.put(0, 1L);

        long curModVal = 1L;
        //(n*(n+1))/2= 10^14.. then n = 14142135
        //So maximum power of i would be 14142135/30 = 471404
        //Hence I am taking max power 471410 for safe side
        for (int i = 1; i <= 471410; i++) {
            curModVal = (curModVal * maxModVal) % M;
            powerHash.put(i, curModVal);
        }
    }
}