package binary.representation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BinaryRepresentation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        int testCaseCount = Integer.parseInt(reader.readLine());
        int[] inputs = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            inputs[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.stream(inputs).forEach(BinaryRepresentation::calculateBinary);
    }

    private static void calculateBinary(int numberInDecimal) {
        Stack<Integer> stack = new Stack<>();
        while (numberInDecimal != 0) {
            stack.push(numberInDecimal % 2);
            numberInDecimal = numberInDecimal / 2;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }




        System.out.println();
    }
}
