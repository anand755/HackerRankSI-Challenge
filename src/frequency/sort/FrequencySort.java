package frequency.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FrequencySort {
    //TODO This code is Working.. But need better solution
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arraySize = Integer.parseInt(reader.readLine());
            Integer[] arrayInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            sortByFrequency(arrayInput, arraySize);
        }
    }

    private static void sortByFrequency(Integer[] arrayInput, int arraySize) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        Map<Integer, Integer> sortedMap = new HashMap<>();
        for (int num : arrayInput) {
            if (!frequencyMap.containsKey(num)) {
                frequencyMap.put(num, 1);
            } else {
                int count = frequencyMap.get(num);
                frequencyMap.put(num, count + 1);
            }
        }
        sortedMap = frequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));

        Map<Integer, List<Integer>> finalMap = new HashMap<>();

        for (Map.Entry entry : sortedMap.entrySet()) {
            int content = Integer.parseInt(entry.getKey().toString());
            int freq = Integer.parseInt(entry.getValue().toString());

            if (finalMap.containsKey(freq)) {
                List<Integer> currList = finalMap.get(freq);
                currList.add(content);
                finalMap.put(freq, currList);
            } else {
                List<Integer> singleList = new ArrayList<>();
                singleList.add(content);
                finalMap.put(freq, singleList);
            }
        }

        for (Map.Entry entry : finalMap.entrySet()) {
            int frequency = Integer.parseInt(entry.getKey().toString());
            List<Integer> numList = (List<Integer>) entry.getValue();
            Collections.sort(numList);
            for (int num : numList) {
                for (int i = 0; i < frequency; i++)
                    System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
