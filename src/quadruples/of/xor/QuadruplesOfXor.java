package quadruples.of.xor;

import java.io.*;
import java.util.*;

public class QuadruplesOfXor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arraySize = Integer.parseInt(reader.readLine());
            int[] arrA = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] arrB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] arrC = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] arrD = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int ans = getXorPairCount(arrA, arrB, arrC, arrD, arraySize);
            writer.write(ans + "\n");
            writer.flush();
        }
    }

    private static int getXorPairCount(int[] arrA, int[] arrB, int[] arrC, int[] arrD, int arraySize) {
        int count = 0;
        Map<Integer, Integer> mapXorAB = new HashMap<>();

        List<Integer> xorListCD = new ArrayList<>();

        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                int ab = arrA[i] ^ arrB[j];
                if (mapXorAB.containsKey(ab)) {
                    int currCount = mapXorAB.get(ab);
                    mapXorAB.put(ab, currCount + 1);
                } else {
                    mapXorAB.put(ab, 1);
                }

                int cd = arrC[i] ^ arrD[j];
                xorListCD.add(cd);
            }
        }

        for (int cd : xorListCD) {
            if (mapXorAB.containsKey(cd)) {
                int currCount = mapXorAB.get(cd);
                count += currCount;
            }
        }
        return count;
    }
}
