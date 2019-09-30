package power.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PowerGame {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrSize = Integer.parseInt(reader.readLine());
            Integer[] arrA = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            Integer[] arrB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            Arrays.sort(arrA);
            Arrays.sort(arrB);
            checkAWinCount(arrA, arrB, arrSize);
        }
    }

    private static void checkAWinCount(Integer[] arrA, Integer[] arrB, int arrSize) {
        int p1 = arrSize - 1, p2 = arrSize - 1;

        int count = 0;
        while ((p1 >= 0) && (p2 >= 0)) {
            if (arrA[p1] > arrB[p2]) {
                p1--;
                p2--;
                count++;
            } else {
                p2--;
            }
        }
        System.out.println(count);
    }
}
