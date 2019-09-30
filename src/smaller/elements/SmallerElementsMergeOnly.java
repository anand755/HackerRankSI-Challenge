package smaller.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SmallerElementsMergeOnly {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            merge_sort(arrInput, 0, arrSize - 1);
            Arrays.stream(arrInput).forEach(__ -> System.out.print(__ + " "));
            System.out.println();
        }
    }


    private static void merge_sort(int[] arrInput, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        merge_sort(arrInput, lo, mid);
        merge_sort(arrInput, mid + 1, hi);
        merge_array(arrInput, lo, mid, hi);
    }

    private static void merge_array(int[] arrInput, int lo, int mid, int hi) {
        int[] tempArr = new int[hi - lo + 1];
        int p = lo;
        int q = mid + 1;
        int k = 0;

        if (lo == hi) {
            return;
        }
        while ((p <= mid) && (q <= hi)) {
            if (arrInput[p] < arrInput[q]) {
                tempArr[k] = arrInput[p];
                k++;
                p++;
            } else {
                tempArr[k] = arrInput[q];
                k++;
                q++;
            }
        }
        while (p <= mid) {
            tempArr[k++] = arrInput[p++];
        }
        while (q <= hi) {
            tempArr[k++] = arrInput[q++];
        }
        for (int i = 0; i < k; i++) {
            arrInput[lo] = tempArr[i];
            lo++;
        }
    }
}

