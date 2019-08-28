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

        for (int i = 0; i < Math.log(power); i++) {
            if (checkBit(number, i)) {
                ans = ans * x;
            }
            x = x * x;
        }
        return ans;
    }

    private static boolean checkBit(int number, int i) {
        return ((number & (1 << i)) != 0);
    }
}
