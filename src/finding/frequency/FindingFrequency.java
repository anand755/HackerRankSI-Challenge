package finding.frequency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindingFrequency {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int arrSize = Integer.parseInt(reader.readLine());
        int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        int queryCount = Integer.parseInt(reader.readLine());
        Map<Integer, Integer> freqMap = mapFrequency(arrInput, arrSize);
        while (queryCount-- > 0) {
            int number = Integer.parseInt(reader.readLine());
            int frequency = freqMap.getOrDefault(number,0);
            System.out.println(frequency);
        }
    }

    private static Map<Integer, Integer> mapFrequency(int[] arrInput, int arrSize) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arrInput) {
            if (!freqMap.containsKey(num)) {
                freqMap.put(num, 1);
            } else {
                int newCount = freqMap.get(num) + 1;

                freqMap.put(num, newCount);
            }
        }
        return freqMap;
    }
}
