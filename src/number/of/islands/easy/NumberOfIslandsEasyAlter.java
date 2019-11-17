package number.of.islands.easy;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class NumberOfIslandsEasyAlter {
    //This is BFS approach. As here we are using iterative call instead of recursive its working properly.
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

        boolean[][] visited = new boolean[N][M];
        //Arrays.fill(visited, false);


        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == 1) {
                    count++;
                    BFS(mat, i, j, N, M, visited);
                }
            }
        }

        return count;
    }

    private static void BFS(int[][] mat, int i, int j, int N, int M, boolean[][] visited) {


        LinkedList<Pair> queue = new LinkedList<>();

        queue.add(new Pair(i, j));
        visited[i][j] = true;


        while (!queue.isEmpty()) {
            Pair u = queue.poll();

            mat[u.i][u.j] = 0;

            //n->north, s->south, e->east, w->west

            Pair n = new Pair(u.i - 1, u.j);
            Pair ne = new Pair(u.i - 1, u.j + 1);
            Pair e = new Pair(u.i, u.j + 1);
            Pair se = new Pair(u.i + 1, u.j + 1);
            Pair s = new Pair(u.i + 1, u.j);
            Pair sw = new Pair(u.i + 1, u.j - 1);
            Pair w = new Pair(u.i, u.j - 1);
            Pair nw = new Pair(u.i - 1, u.j - 1);

            if (isValid(n, N, M, mat, visited)) {
                visited[n.i][n.j] = true;
                queue.add(n);
            }

            if (isValid(ne, N, M, mat, visited)) {
                visited[ne.i][ne.j] = true;
                queue.add(ne);
            }


            if (isValid(e, N, M, mat, visited)) {
                visited[e.i][e.j] = true;
                queue.add(e);
            }


            if (isValid(se, N, M, mat, visited)) {
                visited[se.i][se.j] = true;
                queue.add(se);
            }


            if (isValid(s, N, M, mat, visited)) {
                visited[s.i][s.j] = true;
                queue.add(s);
            }


            if (isValid(sw, N, M, mat, visited)) {
                visited[sw.i][sw.j] = true;
                queue.add(sw);
            }


            if (isValid(w, N, M, mat, visited)) {
                visited[w.i][w.j] = true;
                queue.add(w);
            }


            if (isValid(nw, N, M, mat, visited)) {
                visited[nw.i][nw.j] = true;
                queue.add(nw);
            }

        }

    }

    private static boolean isValid(Pair n, int N, int M, int[][] mat, boolean[][] visited) {
        return (n.i >= 0 && n.i < N && n.j >= 0 && n.j < M && mat[n.i][n.j] == 1 && !visited[n.i][n.j]);
    }

    private static class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
