package decode.strings;

import java.io.*;

public class DecodeStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int length = Integer.parseInt(reader.readLine());
            String str = reader.readLine().trim();

            int wayCount = getNumberOfWaysToDecrypt(str, length);
            writer.write(wayCount + "\n");
            writer.flush();
        }
        writer.flush();
    }

    private static int getNumberOfWaysToDecrypt(String str, int N) {
        char[] message = ("0" + str).toCharArray();

        int mod = (int) 1E9 + 7;
        int[] dpTable = new int[N + 1];
        dpTable[0] = 0;
        dpTable[1] = 1;


        //Using 1 based index
        //DP State: #Ways to decode a string of length i using first i elements
        //DP Expression:    dp(i) = dp(i-1) + dp(i-2)
        //                                    "(i-1)"+"i" <=25 && "(i-1)"+"i" != "i"
        //Ans : dp[N]


        if (N >= 2) {

            if ((Integer.parseInt(message[1] + "" + message[2]) <= 25) &&
                    ((Integer.parseInt(message[1] + "" + message[2]) != Integer.parseInt(message[2] + "")))) {

                dpTable[2] = 2;
            } else {
                dpTable[2] = 1;
            }

        }


        for (int i = 3; i <= N; i++) {

            int combVal = Integer.parseInt(message[i - 1] + "" + message[i]);

            if ((combVal <= 25) && (combVal != Integer.parseInt(message[i] + ""))) {

                dpTable[i] = (dpTable[i - 1] + dpTable[i - 2]) % mod;
            } else {
                dpTable[i] = dpTable[i - 1];
            }


        }


        return dpTable[N];
    }
}
