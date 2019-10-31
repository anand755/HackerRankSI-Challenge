package kmp.string.matching.algorithm;

import java.io.*;

public class KMPStringMatchingAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String[] AB = reader.readLine().split("\\s");
            String pattern = AB[0].trim();
            String text = AB[1].trim();
            int patternCount = getPatternCount(text.toCharArray(), pattern.toCharArray());
            writer.write(patternCount + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getPatternCount(char[] textArr, char[] patternArr) {

        int count = 0;
        int[] lpsArr = computeLpsArray(patternArr);

        if (textArr.length < patternArr.length) {
            return 0;
        }
        int i = 0, j = 0;

        while (i < textArr.length && j < patternArr.length) {
            if (textArr[i] == patternArr[j]) {
                i++;
                j++;
                if (j == patternArr.length) {
                    count++;
                    j = lpsArr[j - 1];
                }

            } else {
                if (j != 0) {
                    j = lpsArr[j - 1];
                } else {
                    i++;
                }
            }
        }

        return count;
    }


    private static int[] computeLpsArray(char[] s) {

        int[] p = new int[s.length];
        int j = 0;
        p[0] = 0;
        for (int i = 1; i < s.length; i++) {
            while (j > 0 && s[j] != s[i])
                j = p[j - 1];

            if (s[j] == s[i])
                j++;
            p[i] = j;
        }
        return p;
    }
}
