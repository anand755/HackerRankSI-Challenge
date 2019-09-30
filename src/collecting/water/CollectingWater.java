package collecting.water;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CollectingWater {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int buildingCount = Integer.parseInt(reader.readLine());
            int[] buildings = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int waterLevel = getTotalWater(buildings, buildingCount);
            System.out.println(waterLevel);

        }
    }

    private static int getTotalWater(int[] buildings, int buildingCount) {
        if (buildingCount <= 2) {
            return 0;
        }

        int[] leftMax = new int[buildingCount - 2];
        int[] rightMax = new int[buildingCount - 2];

        int leftSideMax = buildings[0];
        for (int i = 1; i < (buildingCount - 1); i++) {
            leftSideMax = Math.max(buildings[i - 1], leftSideMax);
            leftMax[i - 1] = leftSideMax;
        }

        int rightSideMax = buildings[buildingCount - 1];
        for (int i = (buildingCount - 2); i >= 1; i--) {
            rightSideMax = Math.max(buildings[i + 1], rightSideMax);
            rightMax[i - 1] = rightSideMax;
        }

        int totalStoreWater = 0;
        for (int i = 1; i <= (buildingCount - 2); i++) {
            int commonWaterLevel = Math.min(leftMax[i - 1], rightMax[i - 1]);
            int storedWater = commonWaterLevel > buildings[i] ? (commonWaterLevel - buildings[i]) : 0;
            totalStoreWater += storedWater;
        }

        return totalStoreWater;
    }
}
