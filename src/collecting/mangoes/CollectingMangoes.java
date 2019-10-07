package collecting.mangoes;

import java.io.*;
import java.util.Stack;

public class CollectingMangoes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCaseCount = Integer.parseInt(reader.readLine());

        for (int testNo = 1; testNo <= testCaseCount; testNo++) {
            Stack<Integer> mangoStack = new Stack<>();
            Stack<Integer> biggestSizeStack = new Stack<>();

            writer.write("Case " + testNo + ":" + "\n");
            int operationCount = Integer.parseInt(reader.readLine());

            while (operationCount-- > 0) {
                String inputStr = reader.readLine();
                String operationType = inputStr.charAt(0) + "";
                int mangoSize = operationType.equalsIgnoreCase("A") ? Integer.parseInt(inputStr.split("\\s")[1]) : 0;

                String result = performOperation(mangoStack, biggestSizeStack, operationType, mangoSize);
                if (operationType.equalsIgnoreCase("Q")) {

                    writer.write(result + "\n");
                    //writer.flush();
                }
                writer.flush();
            }
        }
    }

    private static String performOperation(Stack<Integer> mangoStack, Stack<Integer> biggestSizeStack, String operationType, int mangoSize) throws IOException {

        switch (operationType) {
            case "A":
                if (mangoSize != 0) {
                    mangoStack.push(mangoSize);
                    if ((biggestSizeStack.isEmpty()) || (mangoSize >= biggestSizeStack.peek())) {
                        biggestSizeStack.push(mangoSize);
                    }
                }

                return "";

            case "R":
                if (!mangoStack.isEmpty()) {
                    int poppedMango = mangoStack.pop();
                    if (poppedMango == biggestSizeStack.peek()) {
                        biggestSizeStack.pop();
                    }
                }
                return "";

            case "Q":
                if (biggestSizeStack.isEmpty()) {
                    return "Empty";
                } else {
                    int biggestSizeMango = biggestSizeStack.peek();
                    return biggestSizeMango + "";
                }

            default:
                return "";

        }
    }
}
