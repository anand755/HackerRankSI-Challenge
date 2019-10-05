package subsequence.sum;

import java.io.*;
import java.util.Arrays;

public class SubsequenceSumAlter {
    //This is meet in the middle algo

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
            //writer.write(count + "\n");
            System.out.println(count);

        }
        //writer.flush();

    }

    private static int getSubseSumCount(int[] arrInput, int arrLength, int minValue, int maxValue) {

        int count1 = 0;
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
            if ((sum >= minValue) && (sum <= maxValue)) {
                count1++;
            }
        }
        System.out.print("TempArr1 : ");
        Arrays.stream(tempArr1).forEach(__ -> System.out.print(__ + " "));
        System.out.println();
        System.out.println("count1 = " + count1);

        int count2 = 0;
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
            if ((sum >= minValue) && (sum <= maxValue)) {
                count2++;
            }
        }


        System.out.print("TempArr2 : ");
        Arrays.stream(tempArr2).forEach(__ -> System.out.print(__ + " "));
        System.out.println();
        System.out.println("count2 = " + count2);

        int count3 = 0;
        for (int i = 0; i < firstLoopCount; i++) {
            for (int j = 0; j < lastLoopCount; j++) {
                int sum = tempArr1[i] + tempArr2[j];
                if ((sum >= minValue) && (sum <= maxValue)) {
                    count3++;
                }
            }
        }

        System.out.println("count3 = " + count3);
        System.out.println();


        int totalCount = count1 + count2 + count3;
        System.out.println("Total Count = " + totalCount);

        return totalCount;
    }

    private static boolean CB(int num, int pos) {
        return ((num & (1 << pos)) == (1 << pos));
    }
}
