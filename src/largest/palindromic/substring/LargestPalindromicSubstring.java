package largest.palindromic.substring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LargestPalindromicSubstring {
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
        //as string.substring doesn't include last index i am adding p2+1
        String str = string.substring(p1, p2 + 1);
        StringBuilder sb = new StringBuilder(str);
        return (str.equalsIgnoreCase(sb.reverse().toString()));
    }
}
