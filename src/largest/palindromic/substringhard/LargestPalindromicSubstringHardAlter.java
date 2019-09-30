package largest.palindromic.substringhard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class LargestPalindromicSubstringHardAlter {
    private static BigInteger[] forwardHashArr;
    private static BigInteger[] backwardHashArr;
    private static int p;

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

        forwardHashArr = new BigInteger[stringLength];
        backwardHashArr = new BigInteger[stringLength];
        p = 3;

        forwardHashArr[0] = BigInteger.valueOf((long) string.charAt(0) * (long) Math.pow(p, 1));
        for (int i = 1; i < stringLength; i++) {
            forwardHashArr[i] = forwardHashArr[i - 1].add(BigInteger.valueOf(((long) string.charAt(i) * (long) Math.pow(p, (i + 1)))));
        }

        backwardHashArr[stringLength - 1] = BigInteger.valueOf((long) string.charAt(stringLength - 1) * (long) Math.pow(p, 1));
        for (int i = stringLength - 2; i >= 0; i--) {
            backwardHashArr[i] = backwardHashArr[i + 1].add(BigInteger.valueOf((long) string.charAt(i) * (long) Math.pow(p, (stringLength - i))));
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

    private static int BS(String string, int N, int c1, int c2) {

        int ans = 0;
        int lo = 0, hi = Math.min(c1, N - c2 - 1);

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isPalindrome(string, c1 - mid, c2 + mid)) {
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

    private static boolean isPalindrome(String string, int p1, int p2) {

        /*BigInteger fHash = forwardHashArr[p2] - forwardHashArr[p1] + (int) ((int) string.charAt(p1) * Math.pow(p, p1 + 1));
        BigInteger bHash = backwardHashArr[p1] - backwardHashArr[p2] + (int) ((int) string.charAt(p2) * Math.pow(p, string.length() - p2));*/
        BigInteger fHash = forwardHashArr[p2].subtract(forwardHashArr[p1]).add(BigInteger.valueOf((long) string.charAt(p1) * (long) Math.pow(p, p1 + 1)));
        BigInteger bHash = backwardHashArr[p1].subtract(backwardHashArr[p2]).add(BigInteger.valueOf((long) string.charAt(p2) * (long) Math.pow(p, string.length() - p2)));

        int spfh = p1 + 1;//Smallest power of prime in forward hash
        int spbh = string.length() - p2; //Smallest power of prime in backward hash

        int diff = Math.abs(spfh - spbh);
        if (spfh < spbh) {
            //fHash = (int) (fHash * Math.pow(p, diff));
            fHash = fHash.multiply(BigInteger.valueOf((long) Math.pow(p, diff)));
        } else {
            //bHash = (int) (bHash * Math.pow(p, diff));
            bHash = bHash.multiply(BigInteger.valueOf((long) Math.pow(p, diff)));
        }
        return (fHash.equals(bHash));
    }
}