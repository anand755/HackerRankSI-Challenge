package subset.sum.problem;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SubsetSumProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NS = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int arrLength = NS[0];
            int requiredSum = NS[1];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            String isSubSetExist = isSubSetExist(arrInput, arrLength, requiredSum);
            writer.write(isSubSetExist + "\n");
            writer.flush();
        }
        writer.flush();
    }

    private static String isSubSetExist(int[] arrInput, int N, int S) {

        boolean[][] dpTable = new boolean[N][S + 1];
        Arrays.sort(arrInput);

        //DP Expression : dp(i,j) => Is there is a subset of sum =j using first i elements from array


        //BaseCondition 1: There is always sum =0 for all the N elements of the array
        // (Ex: If you don't consider any number then sum =0, Hence setting as true)
        for (int i = 0; i <= (N - 1); i++) {
            dpTable[i][0] = true;
        }

        //Base Condition 2: You can not create any arbitrary sum by using only arr[0]
        //Hence setting all the sum as false except arr[0] (arr[0] can create a sum of arr[0])

        for (int j = 1; j <= S; j++) {

            if (j == arrInput[0]) {
                dpTable[0][j] = true;
            } else {
                dpTable[0][j] = false;
            }
            //dpTable[0][j] = false;
        }
        //dpTable[0][arrInput[0]] = true;

        //DP State
        //dp(i,j) = dp(i-1,j) || dp(i-1,j-arr[i])
        for (int i = 1; i <= (N - 1); i++) {
            for (int j = 1; j <= S; j++) {

                if (j < arrInput[i]) {

                    dpTable[i][j] = dpTable[i - 1][j];

                } else {

                    dpTable[i][j] = dpTable[i - 1][j] || dpTable[i - 1][j - arrInput[i]];

                }
            }
        }

        //Answer : Is there exist a sum =S using all the 0 to N-1 elements from the array.
        String isPresent = dpTable[N - 1][S] ? "YES" : "NO";
        return isPresent;
    }
}
