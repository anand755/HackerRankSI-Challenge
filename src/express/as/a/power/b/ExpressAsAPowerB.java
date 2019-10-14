package express.as.a.power.b;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ExpressAsAPowerB {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Set<Long> powerSet = computeAllPowerSet();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            long number = Integer.parseInt(reader.readLine());
            String isPresent = powerSet.contains(number) ? "Yes" : "No";
            writer.write(isPresent + "\n");
            //writer.flush();
        }
        writer.flush();
    }

    private static Set<Long> computeAllPowerSet() {
        Set<Long> powerSet = new HashSet<>();

        int maxBaseRange = (int) Math.pow(10, 4);
        for (int base = 2; base <= maxBaseRange; base++) {
            long currBase = base * base;
            powerSet.add(currBase);
            int loopCount = (int) (Math.log(Math.pow(10, 8)) / Math.log(base)) + 1;
            for (int j = 2; j <= loopCount; j++) {
                currBase = currBase * base;
                powerSet.add(currBase);
            }
        }
        return powerSet;
    }
}
