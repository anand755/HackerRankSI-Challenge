package interleavings;

import java.io.*;

public class Interleavings {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        //while (testCaseCount-- > 0) {
        for (int caseNo = 1; caseNo <= testCaseCount; caseNo++) {
            String[] words = reader.readLine().split("\\s");
            String word1 = words[0];
            String word2 = words[1];
            if (word1.charAt(0) > word2.charAt(0)) {
                String temp = word1;
                word1 = word2;
                word2 = temp;
            }
            writer.write("Case #" + caseNo + ":\n");
            printAllInterLeaving(word1, word2, "", writer);
            writer.flush();
        }
        writer.flush();

    }

    private static void printAllInterLeaving(String word1, String word2, String interLeaved, BufferedWriter writer) throws IOException {

        if (word1.length() == 0 && word2.length() == 0) {
            writer.write(interLeaved + "\n");
        }

        if (word1.length() != 0) {
            printAllInterLeaving(word1.substring(1), word2, interLeaved + word1.charAt(0), writer);
        }
        if (word2.length() != 0) {
            printAllInterLeaving(word1, word2.substring(1), interLeaved + word2.charAt(0), writer);
        }
    }
}
