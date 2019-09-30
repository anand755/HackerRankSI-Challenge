package anagrams.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramsEasy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String[] words = reader.readLine().split("\\s");
            String ans = isAnagrams(words[0], words[1]);
            System.out.println(ans);
        }

    }

    private static String isAnagrams(String word1, String word2) {
        Map<Character, Integer> charMap = new HashMap<>();

        for (Character ch : word1.toCharArray()) {
            if (charMap.containsKey(ch)) {
                int count = charMap.get(ch);
                charMap.put(ch, count + 1);
            } else {
                charMap.put(ch, 1);
            }
        }

        for (Character ch : word2.toCharArray()) {
            if (charMap.containsKey(ch)) {
                int count = charMap.get(ch);
                if (count > 1) {
                    charMap.put(ch, count - 1);
                } else {
                    charMap.remove(ch);
                }
            } else {
                return "False";
            }
        }
        if (charMap.size() == 0) {
            return "True";
        } else {
            return "False";
        }
    }
}
