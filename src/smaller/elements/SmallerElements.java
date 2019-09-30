package smaller.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SmallerElements {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            countSmallerElements(arrInput, arrSize);
        }
    }

    private static void countSmallerElements(int[] arrInput, int arrSize) {
        for (int i = 0; i < arrSize; i++) {
            int currentElement = arrInput[i];
            merge_sort(arrInput, i + 1, arrSize - 1, currentElement);
        }

    }

    private static void merge_sort(int[] arrInput, int lo, int hi, int currentElement) {
        if (lo==hi){
            return;
        }
        int mid = (lo + hi) / 2;
        merge_sort(arrInput, lo, mid, currentElement);
        merge_sort(arrInput, mid + 1, hi, currentElement);
        merge_array(arrInput, lo, mid, hi, currentElement);
    }

    private static void merge_array(int[] arrInput, int lo, int mid, int hi, int currentElement) {
        int count = 0;
        int[] tempArr = new int[hi - lo + 1];
        int p = lo;
        int q = mid + 1;
        int k = 0;

        if (lo == hi) {
            return;
        }
        while ((p < mid) && (q < hi)) {
            if (arrInput[p] < arrInput[q]) {
                tempArr[k] = arrInput[p];
                k++;
                p++;
            } else {
                count++;
                tempArr[k] = arrInput[q];
                k++;
                q++;
            }
        }
        while (p < mid) {
            tempArr[k++] = arrInput[p++];
        }
        while (q < hi) {
            tempArr[k++] = arrInput[q++];
        }
        for (int i = 0; i < k; i++) {
            arrInput[lo] = tempArr[i];
        }
        System.out.println(count);
    }
}

