package repeated.numbers;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class RepeatedNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            int arrayLength = Integer.parseInt(reader.readLine());
            int[] inputArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            String repeatedNumbers = getRepeatedNumbers(inputArr, arrayLength);
            writer.write(repeatedNumbers + "\n");
        }
        writer.flush();
    }

    private static String getRepeatedNumbers(int[] inputArr, int arrayLength) {
        int[] naturalArr = IntStream.rangeClosed(1, arrayLength - 2).toArray();
        int bothXor = doXor(inputArr, naturalArr, arrayLength - 2);
        int rightMostSetBit = 0;
        for (int i = 0; i < 31; i++) {
            if (checkBit(bothXor, i)) {
                rightMostSetBit = i;
                break;
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

        return (repeatedNumber1 + " " + repeatedNumber2);
    }

    private static int doXor(int[] arr1, int[] arr2, int arrayLength) {
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
