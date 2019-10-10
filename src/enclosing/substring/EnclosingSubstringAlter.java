package enclosing.substring;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EnclosingSubstringAlter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String[] AB = reader.readLine().split("\\s");
            String pattern = AB[0].trim();
            String text = AB[1].trim();

            int minLength = getMinLengthFromText(pattern.toCharArray(), text.toCharArray());
            writer.write(minLength + "\n");
            //writer.flush();
        }
        writer.flush();
    }

    private static int getMinLengthFromText(char[] pattern, char[] text) {

        boolean isFound = false;
        int minLength = text.length;
        int leftPointer = 0;
        int rightPointer = pattern.length - 1;

        Map<Character, Integer> patternFreqMap = new HashMap<>();
        for (char ch : pattern) {
            int freq = patternFreqMap.getOrDefault(ch, 0);
            patternFreqMap.put(ch, freq + 1);
        }

        Map<Character, Integer> windowMap = new HashMap<>();
        for (int i = 0; i <= rightPointer; i++) {
            int freq = windowMap.getOrDefault(text[i], 0);
            windowMap.put(text[i], freq + 1);
        }


        while ((rightPointer <= text.length - 1) && (leftPointer <= text.length - pattern.length + 1)) {
            if (isValid(windowMap, patternFreqMap)) {
                isFound = true;
                minLength = Math.min(minLength, (rightPointer - leftPointer + 1));

                int freq = windowMap.get(text[leftPointer]);
                freq -= 1;
                if (freq == 0) {
                    windowMap.remove(text[leftPointer]);
                } else {
                    windowMap.put(text[leftPointer], freq);
                }

                leftPointer++;
            } else {

                rightPointer++;
                if (rightPointer == text.length)
                    break;
                int freq = windowMap.getOrDefault(text[rightPointer], 0);
                freq += 1;
                windowMap.put(text[rightPointer], freq);

            }
        }

        return isFound ? minLength : -1;
    }

    private static boolean isValid(Map<Character, Integer> windowFreqMap, Map<Character, Integer> patternFreqMap) {
        Map<Character, Integer> patternMap = new HashMap<>(patternFreqMap);

        Map<Character, Integer> windowMap = new HashMap<>(windowFreqMap);


        for (Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            char pChar = entry.getKey();
            int pCharFreq = entry.getValue();

            if (windowMap.containsKey(pChar)) {
                int windCharFreq = windowMap.get(pChar);
                if (windCharFreq < pCharFreq)
                    return false;
            } else {
                return false;
            }
        }
        return true;
    }
}
