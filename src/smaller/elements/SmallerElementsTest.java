package smaller.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SmallerElementsTest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            //countSmallerElements(arrInput, arrSize);
            int smallerElementsCount = merge_sort(arrInput, 0, arrSize - 1);
            System.out.println(smallerElementsCount);
        }
    }

    private static int merge_sort(int[] arrInput, int lo, int hi) {
        int totalCount = 0;
        if (lo == hi) {
            return 0;
        }
        if (lo <= hi) {
            int mid = (lo + hi) / 2;
            merge_sort(arrInput, lo, mid);
            merge_sort(arrInput, mid + 1, hi);
            totalCount = totalCount + merge_array(arrInput, lo, mid, hi);
            Arrays.stream(arrInput).forEach(__ -> System.out.print(__ + " "));
            System.out.println();

        }
        return totalCount;
    }

    private static int merge_array(int[] arrInput, int lo, int mid, int hi) {
        int count = 0;

        int p = lo, q = mid + 1;

        int[] tempArr = new int[hi - lo + 1];
        int k = 0;

        for (int n = lo; n <= hi; n++) {

            if (p > mid) {
                tempArr[k++] = arrInput[q++];
            } else if (q > hi) {
                tempArr[k++] = arrInput[p++];
            }
            if (arrInput[p] < arrInput[q]) {
                tempArr[k++] = arrInput[p++];
            } else {
                tempArr[k++] = arrInput[q++];
                count++;
            }
        }

        for (int i = 0; i < k; i++) {
            arrInput[lo++] = tempArr[i];
        }
        return count;
    }
}

