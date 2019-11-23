package minimum.time.to.meet;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class MinimumTimeToMeet {
    private static int R, C, N;
    private static int[][] mat;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] RCN = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            R = RCN[0];
            C = RCN[1];
            N = RCN[2];
            mat = new int[R][C];

            for (int r = 0; r < R; r++) {
                mat[r] = Arrays.stream(reader.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
            }
            int timeToMeet = getMinTimeToMeet(mat, R, C, N);
            writer.write(timeToMeet + "\n");
            writer.flush();
        }
        //writer.flush();

    }

    private static int getMinTimeToMeet(int[][] mat, int R, int C, int N) {
        int minTimeToMeet = Integer.MAX_VALUE;

        //This stores all the friends position
        Position[] friendsPosArr = new Position[N];

        //Here Key => Start location
        //Value => matrix stores the time taken to reach any position (i,j) in the matrix
        HashMap<Position, int[][]> positionTimeMap = new HashMap<>();


        int frndCount = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == 2) {

                    //Location of friend
                    //startPosition (i,j)
                    Position startPosition = new Position(i, j);
                    friendsPosArr[frndCount++] = startPosition;
                    updateMapForEachStartPos(positionTimeMap, startPosition);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (mat[i][j] != 1) {
                    int currCellTime = 0;
                    for (Position startPos : friendsPosArr) {
                        currCellTime = Math.max(currCellTime, positionTimeMap.get(startPos)[i][j]);
                    }
                    minTimeToMeet = Math.min(minTimeToMeet, currCellTime);
                }

            }
        }

        minTimeToMeet = (minTimeToMeet == Integer.MAX_VALUE && N >= 1) ? -1 : minTimeToMeet;

        return minTimeToMeet;
    }

    private static void updateMapForEachStartPos(HashMap<Position, int[][]> map, Position startPos) {

        int[][] currTimeTable = new int[R][C];
        Arrays.stream(currTimeTable).forEach(r -> Arrays.fill(r, Integer.MAX_VALUE));

        boolean[][] visited = new boolean[R][C];
        int currTime = 1;


        LinkedList<Position> queue = new LinkedList<>();
        queue.add(startPos);
        queue.add(null);
        visited[startPos.x][startPos.y] = true;
        currTimeTable[startPos.x][startPos.y] = 0;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            if (pos == null) {
                if (queue.size() > 0) {
                    queue.add(null);
                    currTime++;
                }
            } else {
                if (isValidIndex(pos.x, pos.y - 1) && !visited[pos.x][pos.y - 1]) {
                    Position w_pos = new Position(pos.x, pos.y - 1);

                    queue.add(w_pos);
                    visited[w_pos.x][w_pos.y] = true;
                    currTimeTable[w_pos.x][w_pos.y] = currTime;
                }

                if (isValidIndex(pos.x - 1, pos.y) && !visited[pos.x - 1][pos.y]) {
                    Position n_pos = new Position(pos.x - 1, pos.y);

                    queue.add(n_pos);
                    visited[n_pos.x][n_pos.y] = true;
                    currTimeTable[n_pos.x][n_pos.y] = currTime;
                }
                if (isValidIndex(pos.x, pos.y + 1) && !visited[pos.x][pos.y + 1]) {
                    Position e_pos = new Position(pos.x, pos.y + 1);

                    queue.add(e_pos);
                    visited[e_pos.x][e_pos.y] = true;
                    currTimeTable[e_pos.x][e_pos.y] = currTime;
                }
                if (isValidIndex(pos.x + 1, pos.y) && !visited[pos.x + 1][pos.y]) {
                    Position s_pos = new Position(pos.x + 1, pos.y);

                    queue.add(s_pos);
                    visited[s_pos.x][s_pos.y] = true;
                    currTimeTable[s_pos.x][s_pos.y] = currTime;
                }
            }
        }
        map.put(startPos, currTimeTable);
    }

    private static boolean isValidIndex(int x, int y) {
        return (x >= 0 && x < R && y >= 0 && y < C && mat[x][y] != 1);
    }

    private static class Position {
        int x;
        int y;

        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
