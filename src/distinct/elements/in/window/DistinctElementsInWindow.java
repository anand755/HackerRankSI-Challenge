package distinct.elements.in.window;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DistinctElementsInWindow {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arraySize = NK[0];
            int windowSize = NK[1];
            int[] arrayInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            printDistinctElement(arrayInput, windowSize);
        }
    }

    private static void printDistinctElement(int[] arrayInput, int windowSize) throws IOException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int j = 0; j < windowSize; j++) {
            if (hashMap.containsKey(arrayInput[j])) {
                int count = hashMap.get(arrayInput[j]);
                hashMap.put(arrayInput[j], count + 1);
            } else {
                hashMap.put(arrayInput[j], 1);
            }
        }
        writer.write(hashMap.size() + " ");
        for (int i = windowSize; i < arrayInput.length; i++) {
            int addingElement = arrayInput[i];
            int removingElement = arrayInput[i - windowSize];

            if (hashMap.containsKey(addingElement)) {
                int count = hashMap.get(addingElement);
                hashMap.put(addingElement, count + 1);
            } else {
                hashMap.put(addingElement, 1);
            }

            int rCount = hashMap.get(removingElement);
            if (rCount > 1) {
                hashMap.put(removingElement, rCount - 1);
            } else {
                hashMap.remove(removingElement);
            }
            writer.write(hashMap.size() + " ");
        }
        writer.write("\n");
        writer.flush();
    }
}
