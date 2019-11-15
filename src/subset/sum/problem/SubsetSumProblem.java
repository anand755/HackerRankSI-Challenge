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

    private static String isSubSetExist(int[] arrInput, int arrLength, int requiredSum) {

        Set<Integer> allPossibleSumSet = new HashSet<>();

        //dp(i) => # set of all the sum that can be formed using first i elements
        //dp(i) = arr[i] , ((dp(i-1)+arr[i]) )

        IndexSumSet[] dpSet = new IndexSumSet[arrLength];
        dpSet[0] = new IndexSumSet(new HashSet<>(arrInput[0]));

        allPossibleSumSet.add(arrInput[0]);


        for (int i = 1; i < arrLength; i++) {

            HashSet<Integer> currSumSet = new HashSet<>();
            currSumSet.add(arrInput[i]);
            allPossibleSumSet.add(arrInput[i]);

            for (int num : dpSet[i - 1].sumSet) {
                currSumSet.add(num + arrInput[i]);
                allPossibleSumSet.add(num + arrInput[i]);
            }

            dpSet[i] = new IndexSumSet(currSumSet);
        }

        String isPresent = allPossibleSumSet.contains(requiredSum) ? "YES" : "NO";

        return isPresent;
    }

    private static class IndexSumSet {

        Set<Integer> sumSet;

        private IndexSumSet(Set<Integer> sumSet) {
            this.sumSet = sumSet;
        }
    }
}
