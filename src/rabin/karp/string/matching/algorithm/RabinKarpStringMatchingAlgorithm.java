package rabin.karp.string.matching.algorithm;

import java.io.*;

public class RabinKarpStringMatchingAlgorithm {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int z = (int) 1e9 + 7;
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String[] AB = reader.readLine().split("\\s");
            String pattern = AB[0].trim();
            String text = AB[1].trim();

            int patternCount = getPatternCount(text, pattern);
            writer.write(patternCount + "\n");
            writer.flush();
        }
        //writer.flush();
    }


    private static int getPatternCount(String text, String pattern) {
        int count = 0;
        int M = (int) 1e9 + 7;

        int textLength = text.length();
        int patternLength = pattern.length();

        if (textLength < patternLength) {
            return 0;
        }


        //Computing Power Array
        int P1 = 3, P2 = 31;
        long[] powerArrHash_1 = new long[textLength];
        long[] powerArrHash_2 = new long[textLength];
        powerArrHash_1[0] = P1;
        powerArrHash_2[0] = P2;

        for (int i = 1; i < textLength; i++) {
            powerArrHash_1[i] = (powerArrHash_1[i - 1] * P1) % M;
            powerArrHash_2[i] = (powerArrHash_2[i - 1] * P2) % M;
        }


        //Computing Pattern And Initial Window Hash Value
        long patternHashVal_1 = 0, patternHashVal_2 = 0, windowHashVal_1 = 0, windowHashVal_2 = 0;

        for (int i = 0; i < patternLength; i++) {
            patternHashVal_1 = (patternHashVal_1 + ((pattern.charAt(i) * powerArrHash_1[i]) % M)) % M;
            patternHashVal_2 = (patternHashVal_2 + ((pattern.charAt(i) * powerArrHash_2[i]) % M)) % M;

            windowHashVal_1 = (windowHashVal_1 + ((text.charAt(i) * powerArrHash_1[i]) % M)) % M;
            windowHashVal_2 = (windowHashVal_2 + ((text.charAt(i) * powerArrHash_2[i]) % M)) % M;
        }


        //Checking at very beginning
        if ((patternHashVal_1 == windowHashVal_1) /*&& (patternHashVal_2 == windowHashVal_2)*/) {
            count++;
        }


        //Checking in the String taking one char from right and removing one char from left
        for (int i = patternLength; i < textLength; i++) {
            char inChar = text.charAt(i);
            char outChar = text.charAt(i - patternLength);

            windowHashVal_1 = (windowHashVal_1 - ((outChar * powerArrHash_1[i - patternLength]) % M) + M) % M;
            windowHashVal_1 = (windowHashVal_1 + ((inChar * powerArrHash_1[i]) % M)) % M;

            windowHashVal_2 = (windowHashVal_2 - ((outChar * powerArrHash_2[i - patternLength]) % M) + M) % M;
            windowHashVal_2 = (windowHashVal_2 + ((inChar * powerArrHash_2[i]) % M)) % M;


            int powerDif = (i - patternLength + 1);

            long formattedPatterHashVal_1 = (patternHashVal_1 * powerArrHash_1[powerDif - 1]) % M;
            long formattedPatterHashVal_2 = (patternHashVal_2 * powerArrHash_2[powerDif - 1]) % M;
            if ((windowHashVal_1 == formattedPatterHashVal_1) && (windowHashVal_2 == formattedPatterHashVal_2)) {
                count++;
            }
        }

        return count;
    }
}
