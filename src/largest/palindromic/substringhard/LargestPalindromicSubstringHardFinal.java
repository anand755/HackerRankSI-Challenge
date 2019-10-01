package largest.palindromic.substringhard;

import java.io.*;

public class LargestPalindromicSubstringHardFinal {
    private static long[] powerArr;
    private static long[] forwardHashArr;
    private static long[] backwardHashArr;
    private static int p = 3;
    private static int z = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int stringLength = Integer.parseInt(reader.readLine());
            computePowerArray(stringLength);

            char[] string = reader.readLine().toCharArray();
            computeHashArray(string, stringLength);

            int length = getLargestPalindromicLength(string, stringLength);
            writer.write(length + "\n");
            writer.flush();
        }
    }

    private static void computePowerArray(int stringLength) {
        powerArr = new long[stringLength];
        powerArr[0] = p;
        for (int i = 1; i < stringLength; i++) {
            powerArr[i] = (powerArr[i - 1] * p) % z;
        }
    }

    private static void computeHashArray(char[] string, int stringLength) {
        forwardHashArr = new long[stringLength];
        backwardHashArr = new long[stringLength];

        forwardHashArr[0] = (string[0] * powerArr[0]) % z;
        for (int i = 1; i < stringLength; i++) {
            forwardHashArr[i] = (forwardHashArr[i - 1] + (string[i] * powerArr[i]) % z) % z;
        }
        backwardHashArr[stringLength - 1] = (string[stringLength - 1] * powerArr[0]) % z;
        for (int i = stringLength - 2; i >= 0; i--) {
            backwardHashArr[i] = (backwardHashArr[i + 1] + (string[i] * powerArr[stringLength - 1 - i]) % z) % z;
        }
    }

    private static int getLargestPalindromicLength(char[] string, int stringLength) {
        int finalAns = 0;
        //Considering elements as center linearly and checking if it is palindrome or not using BS
        int oddAns, evenAns = 1;
        for (int i = 0; i < stringLength; i++) {

            oddAns = BS(string, stringLength, i, i);
            finalAns = Math.max(finalAns, oddAns);

            if ((i < stringLength - 1) && (string[i] == string[i + 1])) {
                evenAns = BS(string, stringLength, i, i + 1);
                finalAns = Math.max(finalAns, evenAns);
            }
        }
        return finalAns;
    }

    private static int BS(char[] string, int stringLength, int c1, int c2) {
        int ans = 0;
        int lo = 0, hi = Math.min(c1, stringLength - c2 - 1);

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isPalindrome(string, stringLength, c1 - mid, c2 + mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return 2 * ans + 1 + (c2 - c1);
    }

    private static boolean isPalindrome(char[] string, int stringLength, int p1, int p2) {
        long fHash = (forwardHashArr[p2] - forwardHashArr[p1] + ((string[p1] * powerArr[p1]) % z) + z) % z;
        long bHash = (backwardHashArr[p1] - backwardHashArr[p2] + ((string[p2] * powerArr[stringLength - 1 - p2]) % z) + z) % z;

        int spfh = p1 + 1;//Smallest power of prime in forward hash
        int spbh = stringLength - p2; //Smallest power of prime in backward hash
        int diff = Math.abs(spfh - spbh);

        if (spfh < spbh) {
            fHash = (fHash * powerArr[diff - 1]) % z;
        } else if (spfh > spbh) {
            bHash = (bHash * powerArr[diff - 1]) % z;
        }
        return (fHash == bHash);
    }
}