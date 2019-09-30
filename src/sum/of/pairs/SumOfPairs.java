package sum.of.pairs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumOfPairs {
    //TODO MERGE SORT NOT WORKING NEED TO VERIFY.. ALTERNATIVE CODE IS WORKING
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NandK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrayLength = NandK[0];
            int sumK = NandK[1];
            int[] arrayInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            mergeSort(arrayInput, 0, arrayLength - 1);
            String isSumPresent = checkIsSumPresent(arrayInput, arrayLength, sumK);
            System.out.println(isSumPresent);
        }
    }

    private static void mergeSort(int[] arrayInput, int lo, int hi) {
        if (lo == hi) {
            return;
        }

        int m = (lo + hi) / 2;
        mergeSort(arrayInput, lo, m);
        mergeSort(arrayInput, m + 1, hi);
        merge(arrayInput, lo, m, hi);
    }

    private static void merge(int[] arrayInput, int lo, int m, int hi) {

        int p1 = lo;
        int p2 = m + 1;
        int[] tempArr = new int[hi - lo + 1];
        int k = 0;

        while ((p1 <= m) && (p2 <= hi)) {
            if (arrayInput[p1] < arrayInput[p2]) {
                tempArr[k++] = arrayInput[p1++];
            } else {
                tempArr[k++] = arrayInput[p2++];
            }
        }

        while (p1 <= m) {
            tempArr[k++] = arrayInput[p1++];
        }
        while (p2 <= hi) {
            tempArr[k++] = arrayInput[p2++];
        }

        for (int i = 0; i < k; i++) {
            arrayInput[lo++] = tempArr[i];
        }
    }

    private static String checkIsSumPresent(int[] arrayInput, int arrayLength, int sumK) {
        int p1 = 0;
        int p2 = arrayLength - 1;

        while (p1 < p2) {
            if (arrayInput[p1] + arrayInput[p2] > sumK) {
                p2--;
            }
            if (arrayInput[p1] + arrayInput[p2] < sumK) {
                p1++;
            }
            if (arrayInput[p1] + arrayInput[p2] == sumK) {
                return "True";
            }
        }
        return "False";
    }
}
