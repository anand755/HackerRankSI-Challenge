package implement.de.que;

import java.io.*;
import java.util.Stack;

public class ImplementDeQue {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int operationCount = Integer.parseInt(reader.readLine());
        Stack<String> firstStack = new Stack<>();
        Stack<String> secondStack = new Stack<>();

        while (operationCount-- > 0) {
            String opStr = reader.readLine().trim();
            String opType = opStr.substring(0, opStr.indexOf('_'));
            String element = opType.equalsIgnoreCase("push") ? opStr.split("\\s")[1] : "";
            String opSide = opType.equalsIgnoreCase("push") ? opStr.split("\\s")[0].substring(opStr.indexOf('_') + 1) : opStr.substring(opStr.indexOf('_') + 1);

            String ans = performOperation(opType, opSide, element, firstStack, secondStack);
            writer.write(ans);
            //writer.flush();
        }
        writer.flush();
    }

    private static String performOperation(String opType, String opSide, String element, Stack<String> firstStack, Stack<String> secondStack) {
        if (opSide.equalsIgnoreCase("front") && secondStack.isEmpty()) {

            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop());
            }
        }
        if (opSide.equalsIgnoreCase("back") && firstStack.isEmpty()) {
            while (!secondStack.isEmpty()) {
                firstStack.push(secondStack.pop());
            }
        }

        if (opType.equalsIgnoreCase("push")) {
            if (opSide.equalsIgnoreCase("front")) {
                secondStack.push(element);
            } else {
                firstStack.push(element);
            }
        }

        if (opType.equalsIgnoreCase("pop")) {
            if (opSide.equalsIgnoreCase("front")) {
                return secondStack.isEmpty() ? "Empty\n" : secondStack.pop() + "\n";
                //secondStack.pop();
            } else {
                return firstStack.isEmpty() ? "Empty\n" : firstStack.pop() + "\n";
                //firstStack.pop();
            }
        }

        return "";
    }
}
