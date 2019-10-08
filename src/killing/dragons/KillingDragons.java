package killing.dragons;

import java.io.*;
import java.util.Arrays;

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
            //writer.flush();
        }
        writer.flush();
    }

    private static int getStartingDungeon(int[] dragonPowerArr, int[] energyPowerArr, int dungeonsCount) {

        for (int i = 0; i < dungeonsCount; i++) {
            int p, q, power;
            p = q = i;
            power = 0;
            boolean flag = true;

            for (int j = i; j < dungeonsCount; j++) {
                power += energyPowerArr[q];
                if (power >= dragonPowerArr[p]) {
                    power -= dragonPowerArr[p];
                    p++;
                    q++;
                    //continue;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                p = q = 0;
                for (int k = 0; k < i; k++) {
                    power += energyPowerArr[q];
                    if (power >= dragonPowerArr[p]) {
                        power -= dragonPowerArr[p];
                        p++;
                        q++;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                return (i + 1);
            }
        }
        return -1;
    }
}
