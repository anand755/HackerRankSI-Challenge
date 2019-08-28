package check.power.of.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CheckPowerOfTwo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        long[] inputs = new long[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            inputs[i] = Long.parseLong(reader.readLine());
        }
        Arrays.stream(inputs).forEach(CheckPowerOfTwo::validate);
    }

    private static void validate(long number) {
        for (int i = 0; i < 64; i++) {
            if ((number >= 1l << i) && ((1l << i) == number)) {
                System.out.println("True");
                return;
            }
        }
        System.out.println("False");
    }
}
