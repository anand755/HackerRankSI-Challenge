package reverse.the.sentence;

import java.io.*;
import java.util.Stack;

public class ReverseTheSentence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String inputStr = reader.readLine().trim();
            printInReverse(inputStr);
        }
    }

    private static void printInReverse(String inputStr) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<String> stack = new Stack<>();

        if (inputStr.contains(" ")) {
            String[] stringArr = inputStr.split("\\s");
            for (String word : stringArr) {
                stack.push(word);
            }
            int stackSize = stack.size();
            while (stackSize-- > 0) {
                writer.write(stack.pop() + " ");
            }
            writer.write("\n");
            //writer.flush();
        } else {
            writer.write(inputStr + "\n");
            //writer.flush();
        }
        writer.flush();
    }
}
