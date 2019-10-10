package smaller.elements;

import java.io.*;
import java.util.Arrays;

public class SmallerElements {

    private static long totalCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            totalCount = 0;
            merge_sort(arrInput, 0, arrSize - 1);
            writer.write(totalCount + "\n");
            //writer.flush();
        }
        writer.flush();
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
            if (arrInput[q] < arrInput[p]) {
                //If arrInput[q] < arrInput[p] == true then all the elements from p to m is also bigger than arrInput[q]

                totalCount += (mid - p + 1);
                tempArr[k] = arrInput[q];
                k++;
                q++;
            } else {
                tempArr[k] = arrInput[p];
                k++;
                p++;
            }
        }

        //Copying the remaining element to temp array
        while (p <= mid) {
            tempArr[k++] = arrInput[p++];
        }
        while (q <= hi) {
            tempArr[k++] = arrInput[q++];
        }

        //Finally copying from tempArr to original array
        for (int i = 0; i < k; i++) {
            arrInput[lo] = tempArr[i];
            lo++;
        }
    }
}


