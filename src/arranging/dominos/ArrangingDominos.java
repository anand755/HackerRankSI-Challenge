package arranging.dominos;

import java.io.*;

public class ArrangingDominos {

    private static int N = (int) 1E6;
    private static int[] dpTable = new int[N + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        getAllPossibleWayToFillFloor();
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int floorLength = Integer.parseInt(reader.readLine());

            int numberOfWaysToFill = dpTable[floorLength];

            writer.write(numberOfWaysToFill + "\n");
            writer.flush();

        }
        //writer.flush();
    }

    private static void getAllPossibleWayToFillFloor() {

        int mod = (int) 1E9 + 7;


        dpTable[0] = 1;
        dpTable[1] = 1;
        dpTable[2] = 2;
        dpTable[3] = 3;
        dpTable[4] = 5;

        //Dp State: dp(i) represents the number of ways to fill ith position

        //Dp Expression : dp(i) => dp(i-1)+dp(i-2)+dp(i-5)*8
        //Explanation : dp(i-5)*8=> when placing horizontally there are 8 combination using
        //width of 1 and 2 dominos (11111, 1112, 1121, 1211, 2111, 221, 212, 122)

        for (int i = 5; i <= N; i++) {
            long possibleWay = (long) dpTable[i - 1] + (long) dpTable[i - 2] + (long) dpTable[i - 5] * 8;
            dpTable[i] = (int) (possibleWay % mod);

        }
    }
}
