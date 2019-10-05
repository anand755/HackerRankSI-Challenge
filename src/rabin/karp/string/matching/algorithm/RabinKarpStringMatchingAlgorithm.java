package rabin.karp.string.matching.algorithm;

import java.io.*;

public class RabinKarpStringMatchingAlgorithm {
    private static long[] powerArr;
    private static long[] patternHashArr;
    private static int z = (int) 1e9 + 7;
    private static int p = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String[] AB = reader.readLine().split("\\s");
            String pattern = AB[0];
            String text = AB[1];
            computePowerArray(pattern.length() + text.length() - 1);
            long patternHashVal = getHashValue(pattern.toCharArray());
            int patternCount = getPatternCount(text.toCharArray(), pattern.length(), patternHashVal);
            writer.write(patternCount + "\n");
            writer.flush();
        }
    }

    private static void computePowerArray(int length) {
        powerArr = new long[length];
        powerArr[0] = p;

        for (int i = 1; i < length; i++) {
            powerArr[i] = (powerArr[i - 1] * p) % z;
        }
    }

    private static long getHashValue(char[] string) {

        long hashVal = 0;
        for (int i = 0; i < string.length; i++) {
            hashVal = (hashVal + ((string[i] * powerArr[i]) % z)) % z;
        }
        return hashVal;
    }

    private static int getPatternCount(char[] text, int patternLength, long patternHashVal) {
        int count = 0;

        int textLength = text.length;
        //long textHashVal = getHashValue(text.substring(0, patternLength).toCharArray());
        long textHashVal = getHashValue(text.toString().substring(0, patternLength).toCharArray());

        if (textHashVal == patternHashVal)
            count++;

        long[] textHashArr = new long[textLength];

        textHashArr[0] = (text[0] * powerArr[0]) % z;

        for (int i = 1; i < textLength; i++) {
            textHashArr[i] = (textHashArr[i - 1] + (text[i] * powerArr[i]) % z) % z;
        }


        for (int i = patternLength; i < textLength; i++) {

            //textHashVal = textHashVal +

        }

        return 0;
    }


}
