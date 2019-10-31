package interleavings;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Interleavings {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        List<String> wordList;

        for (int caseNo = 1; caseNo <= testCaseCount; caseNo++) {
            String[] words = reader.readLine().split("\\s");
            String word1 = words[0];
            String word2 = words[1];
            writer.write("Case #" + caseNo + ":\n");
            wordList = new ArrayList<>();

            printAllInterLeaving(word1, word2, "", wordList);
            Collections.sort(wordList);
            for (String word : wordList) {
                writer.write(word + "\n");
            }
            writer.flush();
        }
        //writer.flush();
    }

    private static void printAllInterLeaving(String word1, String word2, String interLeaved, List<String> wordList) {

        if (word1.length() == 0 && word2.length() == 0) {
            wordList.add(interLeaved);
            return;
        }

        if (word1.length() != 0) {
            printAllInterLeaving(word1.substring(1), word2, interLeaved + word1.charAt(0), wordList);
        }

        if (word2.length() != 0) {
            printAllInterLeaving(word2.substring(1), word1, interLeaved + word2.charAt(0), wordList);
        }
    }
}
