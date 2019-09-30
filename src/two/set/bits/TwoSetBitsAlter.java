package two.set.bits;

import java.io.*;

public class TwoSetBitsAlter {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = (int) 1e9 + 7;
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
                int y = (int) x;
                lowerSetBitIndex = (int) (pos - (y * (y + 1) / 2)) - 1;
            }

            //System.out.println("Higher Bit : " + higherSetBitIndex + " and Lower Bit : " + lowerSetBitIndex);

            long result = (((1L << lowerSetBitIndex) % M) + ((1L << higherSetBitIndex) % M)) % M;
            writer.write(result + "\n");
            writer.flush();
        }
    }
}
