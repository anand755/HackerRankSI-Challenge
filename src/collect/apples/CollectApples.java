package collect.apples;

import java.io.*;
import java.util.Arrays;

public class CollectApples {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NM = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int rowCount = NM[0];
            int colCount = NM[1];

            int[][] maze = new int[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                maze[i] = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            }

            int collectedApple = getMaxCollectedApple(maze, rowCount, colCount);
            writer.write(collectedApple + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getMaxCollectedApple(int[][] maze, int N, int M) {
        int[][] dpTable = new int[N][M];

        dpTable[0][0] = maze[0][0];

        for (int i = 1; i < N; i++) {
            dpTable[i][0] = dpTable[i - 1][0] + maze[i][0];
        }

        for (int j = 1; j < M; j++) {
            dpTable[0][j] = dpTable[0][j - 1] + maze[0][j];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i][j - 1]) + maze[i][j];
            }
        }

        return dpTable[N - 1][M - 1];
    }
}
