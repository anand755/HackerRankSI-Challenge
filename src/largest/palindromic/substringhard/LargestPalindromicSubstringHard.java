package largest.palindromic.substringhard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LargestPalindromicSubstringHard {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int stringLength = Integer.parseInt(reader.readLine());
            String string = reader.readLine();

            int length = getLargestPalindromicLength(string, stringLength);
            System.out.println(length);
        }

    }

    private static int getLargestPalindromicLength(String string, int stringLength) {
        int ans = 0;

        //Creating hash array
        int[] forwardHashArr = new int[stringLength];
        int[] backwardHashArr = new int[stringLength];

        int p = 13;

        forwardHashArr[0] = (int) ((int) string.charAt(0) * Math.pow(p, 1));
        for (int i = 1; i < stringLength; i++) {
            forwardHashArr[i] = forwardHashArr[i - 1] + (int) ((int) string.charAt(i) * Math.pow(p, (i + 1)));
        }

        backwardHashArr[stringLength - 1] = (int) ((int) string.charAt(stringLength - 1) * Math.pow(p, 1));
        for (int i = stringLength - 2; i >= 0; i--) {
            backwardHashArr[i] = backwardHashArr[i + 1] + (int) ((int) string.charAt(i) * Math.pow(p, (stringLength - i)));
        }

        //Considering elements as center linearly and checking if it is palindrome or not using BS

        for (int i = 0; i < stringLength; i++) {
            int currOddAns = BS(forwardHashArr, backwardHashArr, string, stringLength, i, i);
            int currEvenAns = 0;
            if (i < stringLength - 1) {
                currEvenAns = BS(forwardHashArr, backwardHashArr, string, stringLength, i, i + 1);
            }

            ans = Math.max(ans, currOddAns);
            ans = Math.max(ans, currEvenAns);
        }

        return ans;
    }

    private static int BS(int[] forwardHashArr, int[] backwardHashArr, String string, int N, int c1, int c2) {

        int ans = 0;
        int lo = 0, hi = Math.min(c1, N - c2 - 1);

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isPalindrome(forwardHashArr, backwardHashArr, string, c1 - mid, c2 + mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return 2 * ans + 1 + (c2 - c1);
    }

    private static boolean isPalindrome(int[] forwardHashArr, int[] backwardHashArr, String string, int p1, int p2) {

        int p = 13;

        int fHash = forwardHashArr[p2] - forwardHashArr[p1] + (int) ((int) string.charAt(p1) * Math.pow(p, p1 + 1));

        int bHash = backwardHashArr[p1] - backwardHashArr[p2] + (int) ((int) string.charAt(p2) * Math.pow(p, string.length() - p2));


        int spfh = p1 + 1;//Smallest power of prime in forward hash
        int spbh = string.length() - p2; //Smallest power of prime in backward hash

        int diff = Math.abs(spfh - spbh);

        if (spfh < spbh) {
            fHash = (int) (fHash * Math.pow(p, diff));
        } else {
            bHash = (int) (bHash * Math.pow(p, diff));
        }

        return (fHash == bHash);
    }
}
