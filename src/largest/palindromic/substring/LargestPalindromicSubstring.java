package largest.palindromic.substring;

import java.io.*;

public class LargestPalindromicSubstring {
    //This is the most optimized code
    private static long[] powerArr;
    private static long[] forwardHashArr;
    private static long[] backwardHashArr;
    private static int z = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int stringLength = Integer.parseInt(reader.readLine());
            char[] string = reader.readLine().toCharArray();
            computePowerAndHashArray(string, stringLength);

            int length = getLargestPalindromicLength(string, stringLength);
            writer.write(length + "\n");
            writer.flush();
        }
    }

    private static void computePowerAndHashArray(char[] string, int stringLength) {
        int p = 3;

        powerArr = new long[stringLength];
        forwardHashArr = new long[stringLength];
        backwardHashArr = new long[stringLength];

        powerArr[0] = p;
        forwardHashArr[0] = (string[0] * powerArr[0]) % z;
        backwardHashArr[stringLength - 1] = (string[stringLength - 1] * powerArr[0]) % z;

        for (int i = 1; i < stringLength; i++) {
            powerArr[i] = (powerArr[i - 1] * p) % z;
            forwardHashArr[i] = (forwardHashArr[i - 1] + (string[i] * powerArr[i]) % z) % z;
            backwardHashArr[stringLength - 1 - i] = (backwardHashArr[stringLength - i] + (string[stringLength - 1 - i] * powerArr[i]) % z) % z;
        }

    }


    private static int getLargestPalindromicLength(char[] string, int stringLength) {

        int finalAns = 0;
        //Considering elements as center one by one and checking if it is palindrome or not using BS
        int oddAns, evenAns;
        int loopCount = stringLength - 1;

        for (int i = 0; i < loopCount; i++) {

            oddAns = BS(string, stringLength, i, i);
            finalAns = Math.max(finalAns, oddAns);

            if (string[i] == string[i + 1]) {
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
        long forwardHashVal = (forwardHashArr[p2] - forwardHashArr[p1] + ((string[p1] * powerArr[p1]) % z) + z) % z;
        long backwardHashVal = (backwardHashArr[p1] - backwardHashArr[p2] + ((string[p2] * powerArr[stringLength - 1 - p2]) % z) + z) % z;

        int smallestPowerInForwardHash = p1 + 1;//Smallest power of prime in forward hash
        int smallestPowerInBackwardHash = stringLength - p2; //Smallest power of prime in backward hash
        int powerDiff = Math.abs(smallestPowerInForwardHash - smallestPowerInBackwardHash);

        if (smallestPowerInForwardHash < smallestPowerInBackwardHash) {
            forwardHashVal = (forwardHashVal * powerArr[powerDiff - 1]) % z;
        } else if (smallestPowerInForwardHash > smallestPowerInBackwardHash) {
            backwardHashVal = (backwardHashVal * powerArr[powerDiff - 1]) % z;
        }
        return (forwardHashVal == backwardHashVal);
    }
}