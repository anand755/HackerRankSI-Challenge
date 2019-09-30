package smaller.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SmallerElementsMergeAndCount {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int initialCount = 0;
            int count = merge_sort(arrInput, 0, arrSize - 1);
            Arrays.stream(arrInput).forEach(__ -> System.out.print(__ + " "));
            System.out.println();
            //System.out.println("Count = " + count);
            System.out.println("Count = " + Counter.counter);
        }
    }


    private static int merge_sort(int[] arrInput, int lo, int hi) {
        int totalCount = 0;
        if (lo == hi) {
            return 0;
        }
        int mid = (lo + hi) / 2;
        merge_sort(arrInput, lo, mid);
        merge_sort(arrInput, mid + 1, hi);
        totalCount = totalCount + merge_array(arrInput, lo, mid, hi);
        return totalCount;
    }

    //TODO LAST TIME WAS DOING THIS
    private static int merge_array(int[] arrInput, int lo, int mid, int hi) {
        int count = 0;

        int[] tempArr = new int[hi - lo + 1];
        int p = lo;
        int q = mid + 1;
        int k = 0;

        if (lo == hi) {
            return count;
        }

        //This while loop also works
        /*while ((p <= mid) && (q <= hi)) {
            if (arrInput[q] < arrInput[p]) {
                count++;
                tempArr[k] = arrInput[q];
                k++;
                q++;
            } else {
                tempArr[k] = arrInput[p];
                k++;
                p++;
            }
        }
        while (p <= mid) {
            tempArr[k++] = arrInput[p++];
        }
        while (q <= hi) {
            tempArr[k++] = arrInput[q++];
        }*/


        //Below for loop also works
        for (int n = lo; n <= hi; n++) {
            if (p > mid) {
                tempArr[k] = arrInput[q];
                k++;
                q++;
            } else if (q > hi) {
                tempArr[k] = arrInput[p];
                k++;
                p++;
            } else if (arrInput[q] < arrInput[p]) {
                //count++;
                count = count + (mid - p + 1);
                tempArr[k] = arrInput[q];
                k++;
                q++;
            } else {
                tempArr[k] = arrInput[p];
                k++;
                p++;
            }
        }


        for (int i = 0; i < k; i++) {
            arrInput[lo] = tempArr[i];
            lo++;
        }
        Counter.counter+=count;
        //System.out.println(Counter.counter);
        return count;
    }
}

class Counter {
    public static int counter = 0;

}

