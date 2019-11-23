package minimum.time.to.meet;

import java.io.*;
import java.util.Arrays;

public class MinimumTimeToMeet {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] RCN = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int R = RCN[0];
            int C = RCN[1];
            int N = RCN[2];
            int[][] mat = new int[R][C];

            for (int r = 0; r < R; r++) {
                mat[r] = Arrays.stream(reader.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
            }
            int timeToMeet = getMinTimeToMeet(mat, R, C, N);
            writer.write(timeToMeet + "\n");
            writer.flush();
        }
        writer.flush();

    }

    private static int getMinTimeToMeet(int[][] mat, int R, int C, int N) {

        return 0;
    }
}
