package number.of.valid.subarrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NumberOfValidSubarrays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arraySize = Integer.parseInt(reader.readLine());
            int[] arrayInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int ans = getSubArrayCount(arrayInput, arraySize);
            System.out.println(ans);
        }
    }

    private static int getSubArrayCount(int[] arrayInput, int arraySize) {
        int count = 0;

        int[] prefixSumArr = new int[arraySize];
        prefixSumArr[0] = arrayInput[0] == 0 ? -1 : 1;

        for (int i = 1; i < arraySize; i++) {
            if (arrayInput[i] == 0) {
                prefixSumArr[i] = prefixSumArr[i - 1] + (-1);
            } else {
                prefixSumArr[i] = prefixSumArr[i - 1] + (1);
            }
        }

        Map<Integer, List<Integer>> freqMap = new HashMap<>();

        for (int i = 0; i < arraySize; i++) {
            if (freqMap.containsKey(prefixSumArr[i])) {
                List<Integer> currList = freqMap.get(prefixSumArr[i]);
                currList.add(i);
                freqMap.put(prefixSumArr[i], currList);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                freqMap.put(prefixSumArr[i], newList);
            }
        }


        for (Map.Entry entry : freqMap.entrySet()) {
            List<Integer> list = (List<Integer>) entry.getValue();
            int size = list.size();


            /*size = size - 1;
            count += (size * (size + 1)) / 2;*/
            //or below one
            count += (size * (size - 1)) / 2;

        }
        for (Map.Entry entry : freqMap.entrySet()) {
            if (Integer.valueOf(entry.getKey().toString()) == 0) {
                List<Integer> zeroList = (List<Integer>) entry.getValue();
                count += zeroList.size();
            }
        }

        return count;
    }
}
