package kmp.string.matching.algorithm;

import java.io.*;

public class KMPStringMatchingAlgorithmAlterTwo {
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

        int i = 0, j = 0;

        while (i < textArr.length && j < patternArr.length) {
            if (textArr[i] == patternArr[j]) {
                //System.out.println("Same at i = " + i + " and j = " + j);

                i++;
                j++;
                if (j == patternArr.length) {
                    count++;
                    j = 0;
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

    private static int[] computeLpsArray(char[] patternArr) {
        int[] lps = new int[patternArr.length];
        int j = 0;

        for (int i = 0; i < patternArr.length; ) {
            if (patternArr[i] == patternArr[j]) {
                lps[i] = j + 1;
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
