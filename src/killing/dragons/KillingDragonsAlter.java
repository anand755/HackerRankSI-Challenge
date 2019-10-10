package killing.dragons;

import java.io.*;
import java.util.Arrays;

public class KillingDragonsAlter {
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
            //writer.flush();
        }
        writer.flush();
    }

    private static int getStartingDungeon(int[] dragonPowerArr, int[] energyPowerArr, int totalDungeonsCount) {


        for (int i = 0; i < totalDungeonsCount; i++) {
            int power;
            power = 0;
            boolean isAllDragonKilled = true;

            //Here I am traversing from index i to last

            for (int j = i; j < totalDungeonsCount; j++) {
                power += energyPowerArr[j];
                if (power >= dragonPowerArr[j]) {
                    power -= dragonPowerArr[j];

                } else {
                    isAllDragonKilled = false;
                    break;
                }
            }

            //Here I am traversing from 0 to i index as prince can start from any index in starting
            if (isAllDragonKilled) {
                for (int k = 0; k < i; k++) {
                    power += energyPowerArr[k];
                    if (power >= dragonPowerArr[k]) {
                        power -= dragonPowerArr[k];

                    } else {
                        isAllDragonKilled = false;
                        break;
                    }
                }
            }
            if (isAllDragonKilled) {
                return (i + 1);
            }
        }
        return -1;
    }
}
