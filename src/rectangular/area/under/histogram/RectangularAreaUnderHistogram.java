package rectangular.area.under.histogram;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class RectangularAreaUnderHistogram {
    //This is less optimized code . Having Two for loop .. O(N+N)
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

        int[] nextSmallerBuildingArr = getNextSmallerElement(arrInput, arrLength);
        int[] previousSmallerBuildingArr = getPreviousSmallerElement(arrInput, arrLength);

        for (int i = 0; i < arrLength; i++) {

            int width = (nextSmallerBuildingArr[i] - previousSmallerBuildingArr[i] - 1);

            int currArea = width * arrInput[i];

            area = Math.max(area, currArea);

        }


        return area;
    }

    private static int[] getNextSmallerElement(int[] arrInput, int arrSize) {
        Stack<Integer> stack = new Stack<>();
        int[] arrOutput = new int[arrSize];

        for (int i = 0; i < arrSize; i++) {

            if (stack.isEmpty()) {
                stack.push(i);
            } else {

                while ((!stack.isEmpty()) && (arrInput[stack.peek()] > arrInput[i])) {
                    arrOutput[stack.pop()] = i;
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            arrOutput[stack.pop()] = arrSize;
        }

        return arrOutput;
    }

    private static int[] getPreviousSmallerElement(int[] arrInput, int arrSize) {
        Stack<Integer> stack = new Stack<>();
        int[] arrOutput = new int[arrSize];

        for (int i = arrSize - 1; i >= 0; i--) {

            if (stack.isEmpty()) {
                stack.push(i);
            } else {

                while ((!stack.isEmpty()) && (arrInput[i] < arrInput[stack.peek()])) {
                    arrOutput[stack.pop()] = i;
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            arrOutput[stack.pop()] = -1;
        }

        return arrOutput;
    }
}
