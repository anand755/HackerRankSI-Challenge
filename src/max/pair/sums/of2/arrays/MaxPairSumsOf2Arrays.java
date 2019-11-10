package max.pair.sums.of2.arrays;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MaxPairSumsOf2Arrays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NK[0];
            int outputElementsCount = NK[1];
            int[] arrInputA = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] arrInputB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int[] outPutArr = getMaxPairSum(arrInputA, arrInputB, arrLength, outputElementsCount);
            for (int num : outPutArr) {
                writer.write(num + " ");
            }
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();

    }

    private static int[] getMaxPairSum(int[] arrInputA, int[] arrInputB, int length, int outputNumCount) {
        int[] outPutArr = new int[outputNumCount];


        Arrays.sort(arrInputA);
        Arrays.sort(arrInputB);


        PriorityQueue<SumAndIndex> maxSumHeap = new PriorityQueue<>((x, y) -> y.sum - x.sum);
        Set<String> indexPairSet = new HashSet<>();

        int initialSum = arrInputA[length - 1] + arrInputB[length - 1];
        String initialIndexPairStr = (length - 1) + "," + (length - 1);
        SumAndIndex initialSumIndex = new SumAndIndex(initialSum, initialIndexPairStr);

        maxSumHeap.add(initialSumIndex);
        indexPairSet.add(initialIndexPairStr);

        int k = 0;


        while (k < outputNumCount && !maxSumHeap.isEmpty()) {
            SumAndIndex maxSumAndIndex = maxSumHeap.poll();

            outPutArr[k++] = maxSumAndIndex.sum;

            String[] currPair = maxSumAndIndex.indexPair.split(",");
            int currIndexA = Integer.valueOf(currPair[0]);
            int currIndexB = Integer.valueOf(currPair[1]);


            int nextPair1IndexA = currIndexA;
            int nextPair1IndexB = currIndexB - 1;

            if (nextPair1IndexA >= 0 && nextPair1IndexB >= 0) {
                String nextPossiblePair1 = nextPair1IndexA + "," + nextPair1IndexB;

                if (!indexPairSet.contains(nextPossiblePair1)) {
                    int sum = arrInputA[nextPair1IndexA] + arrInputB[nextPair1IndexB];
                    maxSumHeap.add(new SumAndIndex(sum, nextPossiblePair1));
                    indexPairSet.add(nextPossiblePair1);
                }
            }


            int nextPair2IndexA = currIndexA - 1;
            int nextPair2IndexB = currIndexB;
            if (nextPair2IndexA >= 0 && nextPair2IndexB >= 0) {
                String nextPossiblePair2 = nextPair2IndexA + "," + nextPair2IndexB;

                if (!indexPairSet.contains(nextPossiblePair2)) {
                    int sum = arrInputA[nextPair2IndexA] + arrInputB[nextPair2IndexB];
                    maxSumHeap.add(new SumAndIndex(sum, nextPossiblePair2));
                    indexPairSet.add(nextPossiblePair2);
                }
            }
        }

        return outPutArr;
    }


    private static class SumAndIndex {
        int sum;
        String indexPair;

        private SumAndIndex(int sum, String indexPair) {
            this.sum = sum;
            this.indexPair = indexPair;
        }
    }
}
