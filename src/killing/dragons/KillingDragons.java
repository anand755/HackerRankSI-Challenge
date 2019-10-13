package killing.dragons;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class KillingDragons {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int dungeonsCount = Integer.parseInt(reader.readLine());
            int[] dragonPowerArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] energyPowerArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int startingDungeon = getStartingDungeon(dragonPowerArr, energyPowerArr, dungeonsCount);
            writer.write(startingDungeon + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getStartingDungeon(int[] dragonPowerArr, int[] humanPowerArr, int dungeonsCount) {

        int totalDragonPower = Arrays.stream(dragonPowerArr).sum();
        int totalHumanPower = Arrays.stream(humanPowerArr).sum();
        if (totalHumanPower < totalDragonPower) {
            return -1;
        }


        Deque<Integer> dunQue = new ArrayDeque<Integer>();


        for (int i = 0; i < dungeonsCount; i++) {
            dunQue.addLast(i);
        }
        while (dunQue.peekFirst() != dungeonsCount - 1) {
            while (humanPowerArr[dunQue.peekFirst()] < dragonPowerArr[dunQue.peekFirst()]) {
                dunQue.pollFirst();
            }
            int dragonPower = 0;
            int humanPower = 0;

            int startPos = dunQue.peekFirst();

            boolean isFound = true;

            for (int i = startPos; i < dungeonsCount; i++) {
                dragonPower += dragonPowerArr[i];
                humanPower += humanPowerArr[i];

                if (humanPower < dragonPower) {
                    isFound = false;
                    for (int j = i; j >= startPos; j--) {
                        dunQue.pollFirst();
                    }

                    break;
                }

            }
            if (isFound) {
                break;
            }
        }

        return (dunQue.peekFirst() + 1);
    }

}
