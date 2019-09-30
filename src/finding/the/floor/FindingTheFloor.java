package finding.the.floor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindingTheFloor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int arrSize = Integer.parseInt(reader.readLine());
        int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arrInput);
        int queryCount = Integer.parseInt(reader.readLine());
        while (queryCount-- > 0) {
            int number = Integer.parseInt(reader.readLine());
            int floor = findFloor(arrInput, arrSize, number);
            System.out.println(floor);
        }
    }

    private static int findFloor(int[] arrInput, int arrSize, int number) {

        int lo = 0, hi = arrSize - 1;

        int floor = Integer.MIN_VALUE;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (arrInput[mid] == number) {
                floor = arrInput[mid];
                break;
            } else if (arrInput[mid] < number) {
                lo = mid + 1;
                floor = arrInput[mid];
            } else {
                hi = mid-1;
            }
        }
        return floor;
    }
}
