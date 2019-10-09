package rectangular.area.under.histogram;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class RectangularAreaUnderHistogramAlter {
    //This is more optimized code . Having Single for loop .. O(N)
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int buildingsCount = Integer.parseInt(reader.readLine());
            int[] buildingsHeight = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int largestArea = getLargestArea(buildingsHeight, buildingsCount);
            writer.write(largestArea + "\n");
            //writer.flush();
        }
        writer.flush();
    }

    private static int getLargestArea(int[] arrInput, int arrLength) {
        int area = 0;

        int[] nextSmallerElementIndexArr = new int[arrLength];
        int[] prevSmallerElementIndexArr = new int[arrLength];
        setNextAndPrevSmallerElementIndexArr(arrInput, nextSmallerElementIndexArr, prevSmallerElementIndexArr, arrLength);

        for (int i = 0; i < arrLength; i++) {
            int width = (nextSmallerElementIndexArr[i] - prevSmallerElementIndexArr[i] - 1);
            int currArea = width * arrInput[i];
            area = Math.max(area, currArea);
        }

        return area;
    }

    private static void setNextAndPrevSmallerElementIndexArr(int[] arrInput, int[] nextSmallerElementIndexArr, int[] prevSmallerElementIndexArr, int arrLength) {
        Stack<Integer> stackForNext = new Stack<>();
        Stack<Integer> stackForPrev = new Stack<>();

        for (int i = 0; i < arrLength; i++) {

            //Computing Next Smaller Element Index
            if (stackForNext.isEmpty()) {
                stackForNext.push(i);
            } else {
                while ((!stackForNext.isEmpty()) && (arrInput[stackForNext.peek()] > arrInput[i])) {
                    nextSmallerElementIndexArr[stackForNext.pop()] = i;
                }
                stackForNext.push(i);
            }


            //Computing Previous Smaller Element Index
            int j = (arrLength - 1) - i;
            if (stackForPrev.isEmpty()) {
                stackForPrev.push(j);
            } else {
                while ((!stackForPrev.isEmpty()) && (arrInput[j] < arrInput[stackForPrev.peek()])) {
                    prevSmallerElementIndexArr[stackForPrev.pop()] = j;
                }
                stackForPrev.push(j);
            }

        }
        while (!stackForNext.isEmpty()) {
            nextSmallerElementIndexArr[stackForNext.pop()] = arrLength;
        }

        while (!stackForPrev.isEmpty()) {
            prevSmallerElementIndexArr[stackForPrev.pop()] = -1;
        }
    }
}
