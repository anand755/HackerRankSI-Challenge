package flip.bits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FlipBits {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        int[] flipBitCount = new int[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            int[] input = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int firstNumber = input[0];
            int secondNumber = input[1];
            flipBitCount[i] = calculateFlipBitCount(firstNumber, secondNumber);
        }
        Arrays.stream(flipBitCount).forEach(System.out::println);
    }

    private static int calculateFlipBitCount(int firstNumber, int secondNumber) {

        int counter = 0;
        for (int i = 0; i < 31; i++) {
            if (checkIsSetBit(firstNumber, i) != checkIsSetBit(secondNumber, i))
                counter++;
        }
        return counter;
    }

    private static boolean checkIsSetBit(int number, int position) {

        //Both return statement is correct
        //return ((number & (1 << position)) == (1 << position));
        return ((number & (1 << position)) != 0);
    }
}
