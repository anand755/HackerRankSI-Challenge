package triplet.with.sum.k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TripletWithSumK {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrSize = NK[0];
            int sumK = NK[1];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arrInput);
            boolean isSumPresent = checkForSum(arrInput, arrSize, sumK);
            System.out.println(isSumPresent);
        }
    }

    private static boolean checkForSum(int[] arrInput, int arrSize, int sumK) {

        for (int i = 0; i < arrSize; i++) {
            int C = arrInput[i];
            int requiredSumAB = sumK - C;
            if (findSumByTwoPointer(arrInput, i + 1, arrSize - 1, requiredSumAB)) {
            //if (BinarySearchIter(arrInput, i + 1, arrSize - 1, requiredSumAB)) {
                return true;
            }
        }
        return false;
    }

    private static boolean BinarySearchIter(int[] arrInput, int lo, int hi, int requiredSumAB) {


        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arrInput[mid] == requiredSumAB) {
                return true;
            }
            if (requiredSumAB < arrInput[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }

    private static boolean findSumByTwoPointer(int[] arrInput, int lo, int hi, int requiredSumAB) {
        int p1 = lo;
        int p2 = hi;

        while (p1 < p2) {
            if (arrInput[p1] + arrInput[p2] == requiredSumAB) {
                return true;
            } else if (arrInput[p1] + arrInput[p2] > requiredSumAB) {
                p2--;
            } else {
                p1++;
            }
        }
        return false;
    }
}
