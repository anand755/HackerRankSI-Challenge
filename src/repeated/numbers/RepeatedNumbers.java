package repeated.numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class RepeatedNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            int arrayLength = Integer.parseInt(reader.readLine());
            int[] inputArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            getRepeatedNumbers(inputArr, arrayLength);

        }
    }

    private static void getRepeatedNumbers(int[] inputArr, int arrayLength) {
        int[] naturalArr = IntStream.rangeClosed(1, arrayLength - 2).toArray();
        int bothXor = doXor(inputArr, naturalArr);
        int rightMostSetBit = 0;
        for (int i = 0; i < 31; i++) {
            if (checkBit(bothXor, i)) {
                rightMostSetBit = i;
            }
        }

        int repeatedNumber1 = 0, repeatedNumber2 = 0;
        for (int element : inputArr) {
            if (checkBit(element, rightMostSetBit)) {
                repeatedNumber1 ^= element;
            } else {
                repeatedNumber2 ^= element;
            }
        }

        for (int element : naturalArr) {
            if (checkBit(element, rightMostSetBit)) {
                repeatedNumber1 ^= element;
            } else {
                repeatedNumber2 ^= element;
            }
        }

        repeatedNumber1 = repeatedNumber1 < repeatedNumber2 ?
                repeatedNumber1 : repeatedNumber2 ^ repeatedNumber1 ^ (repeatedNumber2 = repeatedNumber1);

        System.out.println(repeatedNumber1 + " " + repeatedNumber2);
    }

    private static int doXor(int[] arr1, int[] arr2) {
        int resultXor = 0;
        for (int i : arr1) {
            resultXor ^= i;
        }
        for (int i : arr2) {
            resultXor ^= i;
        }
        return resultXor;
    }

    private static boolean checkBit(int number, int index) {
        return ((number & (1 << index)) == (1 << index));
    }
}
