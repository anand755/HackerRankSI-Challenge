package largest.palindromic.substringhard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LargestPalindromicSubstringHard {

    private static long[] forwardHashArr;
    private static long[] backwardHashArr;
    private static int p;
    private static int z;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int stringLength = Integer.parseInt(reader.readLine());
            String string = reader.readLine();

            computeHashArray(string, stringLength);
            int length = getLargestPalindromicLength(string, stringLength);
            System.out.println(length);
        }
    }

    private static void computeHashArray(String string, int stringLength) {

        forwardHashArr = new long[stringLength];
        backwardHashArr = new long[stringLength];
        p = 3;
        z = (int) 1e9 + 7;

        forwardHashArr[0] = ((long) ((long) string.charAt(0) * Math.pow(p, 1))) % z;
        for (int i = 1; i < stringLength; i++) {
            forwardHashArr[i] = (forwardHashArr[i - 1] + (long) ((long) string.charAt(i) * Math.pow(p, (i + 1)))) % z;
        }

        backwardHashArr[stringLength - 1] = ((long) ((long) string.charAt(stringLength - 1) * Math.pow(p, 1))) % z;
        for (int i = stringLength - 2; i >= 0; i--) {
            backwardHashArr[i] = (backwardHashArr[i + 1] + (long) ((long) string.charAt(i) * Math.pow(p, (stringLength - i)))) % z;
        }
    }

    private static int getLargestPalindromicLength(String string, int stringLength) {
        int ans = 0;
        //Considering elements as center linearly and checking if it is palindrome or not using BS

        for (int i = 0; i < stringLength; i++) {
            int currOddAns = BS(string, stringLength, i, i);
            int currEvenAns = 0;
            if (i < stringLength - 1) {
                currEvenAns = BS(string, stringLength, i, i + 1);
            }

            ans = Math.max(ans, currOddAns);
            ans = Math.max(ans, currEvenAns);
        }
        return ans;
    }

    private static int BS(String string, int stringLength, int c1, int c2) {

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

        if ((c2 - c1 == 1) && (string.charAt(c1) != string.charAt(c2))) {
            return 1;
        } else {
            return 2 * ans + 1 + (c2 - c1);
        }
    }

    private static boolean isPalindrome(String string, int stringLength, int p1, int p2) {

        long fHash = forwardHashArr[p2] - forwardHashArr[p1] + ((long) ((long) string.charAt(p1) * Math.pow(p, p1 + 1)) % z);
        long bHash = backwardHashArr[p1] - backwardHashArr[p2] + ((long) ((long) string.charAt(p2) * Math.pow(p, stringLength - p2)) % z);

        int spfh = p1 + 1;//Smallest power of prime in forward hash
        int spbh = stringLength - p2; //Smallest power of prime in backward hash

        int diff = Math.abs(spfh - spbh);
        if (spfh < spbh) {

            fHash = ((int) (fHash * Math.pow(p, diff)));
        } else {
            bHash = ((int) (bHash * Math.pow(p, diff)));

        }
        return (fHash == bHash);
    }
}
