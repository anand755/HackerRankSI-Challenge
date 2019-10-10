package enclosing.substring;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EnclosingSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String[] AB = reader.readLine().split("\\s");
            String pattern = AB[0];
            String text = AB[1].trim();

            int minLength = getMinLengthFromText(pattern, text.toCharArray());
            writer.write(minLength + "\n");
            //writer.flush();
        }
        writer.flush();
    }

    private static int getMinLengthFromText(String pattern, char[] text) {

        boolean isFound = false;
        int minLength = text.length;
        int leftPointer = 0;
        int rightPointer = pattern.length() - 1;

        Map<Character, Integer> patternFreqMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            if (patternFreqMap.containsKey(ch)) {
                int freq = patternFreqMap.get(ch);
                patternFreqMap.put(ch, freq + 1);
            } else {
                patternFreqMap.put(ch, 1);
            }
        }

        /*int[] countArrPattern = new int[26];
        for (char ch : pattern.toCharArray()) {
            countArrPattern[ch-'a'] =
        }*/

        while ((rightPointer <= text.length - 1) && (leftPointer <= text.length - pattern.length() + 1)) {
            if (isValid(text, patternFreqMap, leftPointer, rightPointer)) {
                isFound = true;
                minLength = Math.min(minLength, (rightPointer - leftPointer + 1));
                leftPointer++;
            } else {
                rightPointer++;
            }
        }

        /*int ans = isFound ? minLength : -1;
        return ans;*/
        return isFound ? minLength : -1;
    }

    private static boolean isValid(char[] text, Map<Character, Integer> patternFreqMap, int leftPointer, int rightPointer) {
        Map<Character, Integer> patternMap = new HashMap<>(patternFreqMap);




        for (int i = leftPointer; i <= rightPointer; i++) {
            char currChar = text[i];
            if (patternMap.containsKey(currChar)) {
                int newFreq = patternMap.get(currChar) - 1;

                if (newFreq == 0)
                    patternMap.remove(currChar);
                else
                    patternMap.put(currChar, newFreq);
            }
        }
        return patternMap.isEmpty();
    }
}
