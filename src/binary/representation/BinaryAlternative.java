package binary.representation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinaryAlternative {
    //Working code.. accepted in HR SI
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int number = Integer.parseInt(reader.readLine());
            printInBinaryFormat(number);
        }
    }

    private static void printInBinaryFormat(int number) {
        StringBuilder binaryString = new StringBuilder();
        if (number != 0) {
            int currSum = 0;
            for (int i = 0; (i < 31); i++) {
                if (checkBit(number, i)) {
                    binaryString.append(1);
                    currSum += 1 << i;

                    if (currSum == number) {
                        break;
                    }
                } else {
                    binaryString.append(0);
                }
            }
        } else {
            binaryString.append(0);
        }
        System.out.println(binaryString.reverse());
    }

    private static boolean checkBit(int number, int index) {
        return ((number & (1 << index)) == (1 << index));
    }
}