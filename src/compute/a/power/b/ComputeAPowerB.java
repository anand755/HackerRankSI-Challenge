package compute.a.power.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ComputeAPowerB {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        long[] powerResult = new long[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int number = inputs[0];
            int power = inputs[1];
            powerResult[i] = findPower(number, power);
        }
        Arrays.stream(powerResult).forEach(System.out::println);
    }


    private static long findPower(int number, int power) {
        long x = number, ans = 1L;
        long M = (long) 1e9 + 7;
        for (int i = 0; i < 31; i++) {
            if (checkBit(power, i)) {
                ans = ((ans % M) * (x % M)) % M;
            }
            x = ((x % M) * (x % M)) % M;
        }
        return ans;
    }

    private static boolean checkBit(int number, int i) {
        return ((number & (1 << i)) != 0);
    }
}
