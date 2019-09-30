package pair.with.difference.k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PairWithDifferenceKAlter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrSize = NK[0];
            int diif = NK[1];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arrInput);
            boolean isExist = checkIfDiffExist(arrInput, arrSize, diif);
            System.out.println(isExist);
        }
    }

    private static boolean checkIfDiffExist(int[] arrInput, int arrSize, int diif) {
        for (int num : arrInput) {
            int toFind = num + diif;
            //if (BsrRecursive(arrInput, 0, arrSize - 1, toFind)) {
            if (BsrIterative(arrInput, toFind)) {
                return true;
            }
        }
        return false;
    }

    private static boolean BsrIterative(int[] arrInput, int toFind) {
        int lo = 0, hi = arrInput.length - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arrInput[mid] == toFind) {
                return true;
            }
            if (toFind < arrInput[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return false;
    }

    /*private static boolean BsrRecursive(int[] arrInput, int lo, int hi, int toFind) {
        if (lo > hi) {
            return false;
        }

        int mid = (lo + hi) / 2;
        if (arrInput[mid] == toFind) {
            return true;
        }
        if (arrInput[mid] > toFind) {
            return BsrRecursive(arrInput, lo, mid, toFind);
        }
        if (arrInput[mid] < toFind) {
            return BsrRecursive(arrInput, mid + 1, hi, toFind);
        }
        return false;
    }*/
}
