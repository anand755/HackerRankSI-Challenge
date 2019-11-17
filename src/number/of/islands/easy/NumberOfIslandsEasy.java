package number.of.islands.easy;

import java.io.*;
import java.util.Arrays;

public class NumberOfIslandsEasy {
    //This is DFS approach. This is code is completely okay But its giving RTE only because of deep
    //recursive call. Java online compiler might not always support that.
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] RowCol = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int R = RowCol[0];
            int C = RowCol[1];

            int[][] mat = new int[R][C];
            for (int i = 0; i < R; i++) {

                mat[i] = Arrays.stream(reader.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();

            }

            int islandsCount = getIslandsCount(mat, R, C);
            writer.write(islandsCount + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getIslandsCount(int[][] mat, int N, int M) {

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == 1) {
                    count++;
                    DFS(mat, i, j, N, M);
                }
            }
        }

        return count;
    }

    private static void DFS(int[][] mat, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || mat[i][j] == 0) {
            return;
        }

        mat[i][j] = 0;

        //Using N-8 Connectivity
        DFS(mat, i - 1, j, N, M);
        DFS(mat, i - 1, j + 1, N, M);
        DFS(mat, i, j + 1, N, M);
        DFS(mat, i + 1, j + 1, N, M);
        DFS(mat, i + 1, j, N, M);
        DFS(mat, i + 1, j - 1, N, M);
        DFS(mat, i, j - 1, N, M);
        DFS(mat, i - 1, j - 1, N, M);

    }
}
