package sum.of.pairs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SumOfPairs {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            int[] NS = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int arrLength = NS[0];
            int requiredSum = NS[1];

            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arrInput);
            String isPairFOund = checkForSumInArr(arrInput, requiredSum);
            System.out.println(isPairFOund);
        }
    }

    private static String checkForSumInArr(int[] arrInput, int requiredSum) {
        int p1 = 0, p2 = arrInput.length - 1;

        while (p1 < p2) {

            if (arrInput[p1] + arrInput[p2] == requiredSum) {
                return "True";
            } else if (arrInput[p1] + arrInput[p2] > requiredSum) {
                p2--;
            } else if (arrInput[p1] + arrInput[p2] < requiredSum) {
                p1++;
            }
        }
        return "False";
    }
}
