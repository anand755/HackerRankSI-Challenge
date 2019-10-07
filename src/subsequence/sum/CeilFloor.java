package subsequence.sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class CeilFloor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of test case : ");
        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            System.out.println("Enter the array : ");
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            boolean isContinue = true;
            while (isContinue) {
                System.out.println("Enter number : ");
                int num = Integer.parseInt(reader.readLine());
                System.out.println("Find Ceiling(C) or Floor(F) : ");
                String type = reader.readLine();
                int ans = type.equalsIgnoreCase("C") ? findCeil(arrInput, num) : findFloor(arrInput, num);
                System.out.println("Answer is : " + ans);
                System.out.println("Do you want to continue? (Y/N) : ");
                isContinue = reader.readLine().equalsIgnoreCase("Y");
            }
        }
    }


    private static int findFloor(int[] arrInput, int number) {
        //startIndex inclusive

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
        //startIndex inclusive

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
