package rotting.oranges;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class RottingOranges {
    private static int maxDays = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            maxDays = 0;
            int matSize = Integer.parseInt(reader.readLine());
            int[][] matrix = new int[matSize][matSize];
            for (int i = 0; i < matSize; i++) {
                matrix[i] = Arrays.stream(reader.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
            }
            int days = getMinDaysToRot(matrix, matSize);
            writer.write(days + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getMinDaysToRot(int[][] matrix, int matSize) {
        boolean[][] visited = new boolean[matSize][matSize];


        for (int i = 0; i < matSize; i++) {
            for (int j = 0; j < matSize; j++) {
                if (matrix[i][j] == 2 && isValid(matrix, matSize, i, j)) {

                    BFS(matrix, visited, i, j, matSize);
                }
            }
        }

        int output = maxDays;

        for (int i = 0; i < matSize; i++) {
            for (int j = 0; j < matSize; j++) {
                if (matrix[i][j] == 1) {
                    output = -1;
                }
            }
        }

        return output;
    }

    private static void BFS(int[][] matrix, boolean[][] visited, int i, int j, int N) {
        int currDays = 0;
        LinkedList<Position> queue = new LinkedList<>();
        queue.add(new Position(i, j));
        queue.add(null);
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Position u = queue.poll();


            if (u == null) {
                if (queue.size() > 0) {
                    queue.add(null);
                    currDays++;
                    maxDays = Math.max(currDays, maxDays);
                }

            } else {
                matrix[u.i][u.j] = 2;

                if (isValidIndex(u.i, u.j - 1, N, visited, matrix)) {
                    queue.add(new Position(u.i, u.j - 1));
                    visited[u.i][u.j - 1] = true;
                }
                if (isValidIndex(u.i - 1, u.j, N, visited, matrix)) {
                    queue.add(new Position(u.i - 1, u.j));
                    visited[u.i - 1][u.j] = true;
                }

                if (isValidIndex(u.i, u.j + 1, N, visited, matrix)) {
                    queue.add(new Position(u.i, u.j + 1));
                    visited[u.i][u.j + 1] = true;
                }

                if (isValidIndex(u.i + 1, u.j, N, visited, matrix)) {
                    queue.add(new Position(u.i + 1, u.j));
                    visited[u.i + 1][u.j] = true;
                }
            }
        }
    }

    private static boolean isValidIndex(int i, int j, int N, boolean[][] visited, int[][] matrix) {
        return (i >= 0 && i < N && j >= 0 && j < N && !visited[i][j] && matrix[i][j] == 1);
    }

    private static boolean isValid(int[][] matrix, int N, int i, int j) {
        int w_i = i, w_j = j - 1;
        if (w_i >= 0 && w_i < N && w_j >= 0 && w_j < N && matrix[w_i][w_j] == 1) {
            return true;
        }

        int n_i = i - 1, n_j = j;
        if (n_i >= 0 && n_i < N && n_j >= 0 && n_j < N && matrix[n_i][n_j] == 1) {
            return true;
        }

        int e_i = i, e_j = j + 1;
        if (e_i >= 0 && e_i < N && e_j >= 0 && e_j < N && matrix[e_i][e_j] == 1) {
            return true;
        }

        int s_i = i + 1, s_j = j;
        if (s_i >= 0 && s_i < N && s_j >= 0 && s_j < N && matrix[s_i][s_j] == 1) {
            return true;
        }
        return false;
    }

    private static class Position {
        int i;
        int j;

        private Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
