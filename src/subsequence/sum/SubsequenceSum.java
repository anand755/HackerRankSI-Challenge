package subsequence.sum;

import java.io.*;
import java.util.Arrays;

public class SubsequenceSum {
    //This is meet in the middle algorithm

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NAB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NAB[0];
            int minValue = NAB[1];
            int maxValue = NAB[2];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int count = getSubseSumCount(arrInput, arrLength, minValue, maxValue);
            writer.write(count + "\n");
        }
        writer.flush();
    }

    private static int getSubseSumCount(int[] arrInput, int arrLength, int minValue, int maxValue) {

        int count = 0;

        int fHalf = arrLength / 2;
        int firstLoopCount = 1 << fHalf;
        int[] tempArr1 = new int[firstLoopCount];

        for (int i = 0; i < firstLoopCount; i++) {
            int sum = 0;
            for (int j = 0; j < fHalf; j++) {
                if (CB(i, j)) {
                    sum += arrInput[j];
                }
            }
            tempArr1[i] = sum;
        }


        int lHalf = arrLength - fHalf;
        int lastLoopCount = 1 << lHalf;
        int[] tempArr2 = new int[lastLoopCount];

        for (int i = 0; i < lastLoopCount; i++) {
            int sum = 0;
            for (int j = 0; j < lHalf; j++) {
                if (CB(i, j)) {
                    sum += arrInput[arrLength - 1 - j];
                }
            }
            tempArr2[i] = sum;

        }


        Arrays.sort(tempArr2);
        for (int num : tempArr1) {
            int minReq = minValue - num;
            int maxReq = maxValue - num;

            if (!((minReq > tempArr2[tempArr2.length - 1]) || (maxReq < tempArr2[0]))) {

                int minIndex = findCeil(tempArr2, minReq);
                int maxIndex = findFloor(tempArr2, maxReq);

                count += (maxIndex - minIndex + 1);
            }
        }

        return count;
    }

    private static boolean CB(int num, int pos) {
        return ((num & (1 << pos)) == (1 << pos));
    }

    private static int findFloor(int[] arrInput, int number) {

        int lo = 0, hi = arrInput.length - 1;
        int floorIndex = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (arrInput[mid] == number) {
                floorIndex = mid;
                break;
            } else if (arrInput[mid] < number) {
                lo = mid + 1;
                floorIndex = mid;
            } else {
                hi = mid - 1;
            }

        }
        return floorIndex;
    }

    private static int findCeil(int[] arrInput, int number) {

        int lo = 0, hi = arrInput.length - 1;
        int ceilIndex = arrInput.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arrInput[mid] == number) {
                ceilIndex = mid;
                break;
            } else if (arrInput[mid] > number) {
                hi = mid - 1;
                ceilIndex = mid;
            } else {
                lo = mid + 1;
            }
        }
        return ceilIndex;
    }
}
