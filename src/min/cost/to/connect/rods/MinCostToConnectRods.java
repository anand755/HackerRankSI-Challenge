package min.cost.to.connect.rods;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostToConnectRods {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int minCost = getMinCost(arrInput, arrLength);
            writer.write(minCost + "\n");
            writer.flush();

        }
        writer.flush();
    }

    private static int getMinCost(int[] arrInput, int arrLength) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int num : arrInput) {
            priorityQueue.add(num);
        }
        int totalCost = 0;

        while (!priorityQueue.isEmpty() && priorityQueue.size() >= 2) {
            int length_1 = priorityQueue.poll();
            int length_2 = priorityQueue.poll();

            totalCost += length_1 + length_2;
            priorityQueue.add(length_1 + length_2);

        }


        return totalCost;
    }


}
