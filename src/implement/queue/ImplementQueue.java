package implement.queue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int operationCount = Integer.parseInt(reader.readLine());

        List<String> queueList = new ArrayList<>();

        while (operationCount-- > 0) {

            String opStr = reader.readLine().trim();
            String opType = opStr.contains("Enqueue") ? "Enqueue" : "Dequeue";
            String element = opType.equalsIgnoreCase("Enqueue") ? opStr.split("\\s")[1] : "";
            String ans = performOperation(queueList, opType, element);
            writer.write(ans);
            //writer.flush();
        }
        writer.flush();
    }

    private static String performOperation(List<String> queueList, String opType, String element) {
        if (opType.equalsIgnoreCase("Enqueue")) {
            queueList.add(element);
            return "";
        } else {
            if (queueList.isEmpty())
                return "Empty\n";
            else
                return queueList.remove(0) + "\n";
        }
    }
}
