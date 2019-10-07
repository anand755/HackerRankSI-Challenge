package implement.stack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementStack {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack stack = new Stack();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String operationString = reader.readLine();
            if (operationString.contains("push")) {
                String numStr = operationString.split("\\s")[1];
                int num = Integer.parseInt(numStr);
                stack.push(num);
            } else {
                String poppedNum = stack.pop();
                writer.write(poppedNum + "\n");
            }
        }
        writer.flush();
    }

    private static class Stack {
        List<Integer> list = new ArrayList<>();

        private void push(int num) {
            list.add(num);
        }

        private String pop() {
            if (list.isEmpty()) {
                return "Empty";
            } else {
                int lastIndex = list.size() - 1;
                int lasElement = list.get(lastIndex);
                list.remove(lastIndex);
                return String.valueOf(lasElement);
            }
        }
    }
}
