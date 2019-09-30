package cabinets.partitioning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CabinetsPartitioning {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int taskSize = NK[0];
            int employeeCount = NK[1];
            int[] taskArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int minTime = getMinTimeToComplete(taskArr, taskSize, employeeCount);
            System.out.println(minTime);
        }
    }

    private static int getMinTimeToComplete(int[] taskArr, int taskSize, int employeeCount) {
        //int lo = Arrays.stream(taskArr).sum() / employeeCount;
        int lo = 0;
        //int lo = (int) Collections.max(Arrays.asList(taskArr));
        int hi = Arrays.stream(taskArr).sum();

        int ans = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isValid(taskArr, taskSize, mid, employeeCount)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private static boolean isValid(int[] taskArr, int taskSize, int mid, int employeeCount) {
        //This method checks if taskarray can be splitted into <= employecount partiotioning where sum of task in
        //each partion is mid

        int currTotalTime = 0;
        int currEmpCount = 1;

        for (int task : taskArr) {
            if (task > mid) {
                return false;
            }
            if (currTotalTime + task <= mid) {
                currTotalTime = currTotalTime + task;
            } else if (currTotalTime + task > mid) {
                currEmpCount++;
                currTotalTime = task;
            }
        }
        return (currEmpCount <= employeeCount);
    }
}
