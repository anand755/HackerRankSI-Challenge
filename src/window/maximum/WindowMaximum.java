package window.maximum;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WindowMaximum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {

            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NK[0];
            int windowSize = NK[1];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int totalSum = getSumOfAllWindow(arrInput, arrLength, windowSize);
            writer.write(totalSum + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getSumOfAllWindow(int[] arrInput, int arrLength, int windowSize) {

        int sum = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < windowSize; i++) {

            while ((!deque.isEmpty()) && (arrInput[deque.peekLast()] < arrInput[i])) {
                deque.pollLast();
            }
            deque.addLast(i);

        }
        sum += arrInput[deque.peekFirst()];

        for (int i = windowSize; i < arrLength; i++) {
            while ((!deque.isEmpty()) && (arrInput[deque.peekLast()] < arrInput[i])) {
                deque.pollLast();
            }
            deque.addLast(i);

            while (deque.peekFirst() <= (i - windowSize)) {
                deque.pollFirst();
            }
            sum += arrInput[deque.peekFirst()];
        }
        return sum;
    }
}
