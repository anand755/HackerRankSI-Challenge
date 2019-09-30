package pair.with.difference.k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairWithDifferenceK {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrSize = NK[0];
            int diif = NK[1];
            String[] arrInput = reader.readLine().split("\\s");
            boolean isExist = checkIfDiffExist(arrInput, arrSize, diif);
            System.out.println(isExist);
        }
    }

    private static boolean checkIfDiffExist(String[] arrInput, int arrSize, int diif) {
        for (String num : arrInput) {
            int toFind = Integer.valueOf(num) + diif;
            if (Arrays.asList(arrInput).contains(String.valueOf(toFind))) {
                return true;
            }
        }
        return false;
    }
}
