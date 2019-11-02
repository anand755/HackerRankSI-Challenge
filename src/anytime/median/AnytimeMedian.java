package anytime.median;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AnytimeMedian {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] arrMedian = getMedian(arrInput, arrLength);
            for (int median : arrMedian) {
                writer.write(median + " ");
            }
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int[] getMedian(int[] arrInput, int arrLength) {

        int[] medianArr = new int[arrLength];
        //Overriding comparator to get MaxHeap
        PriorityQueue<Integer> smallHalfMaxHeap = new PriorityQueue<>((x, y) -> y - x);

        PriorityQueue<Integer> biggerHalfMinHeap = new PriorityQueue<>();

        smallHalfMaxHeap.add(arrInput[0]);
        medianArr[0] = arrInput[0];

        for (int i = 1; i < arrLength; i++) {
            if ((!smallHalfMaxHeap.isEmpty()) && (arrInput[i] < smallHalfMaxHeap.peek())) {
                smallHalfMaxHeap.add(arrInput[i]);
            } else {
                biggerHalfMinHeap.add(arrInput[i]);
            }

            //do balance for the size of both heap
            if (biggerHalfMinHeap.size() > smallHalfMaxHeap.size()) {
                int shiftedVal = biggerHalfMinHeap.poll();
                smallHalfMaxHeap.add(shiftedVal);
            }

            medianArr[i] = smallHalfMaxHeap.peek();
        }


        return medianArr;
    }
}
