package path.in.a.matrix;

import java.io.*;
import java.util.Arrays;

public class PathInAMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] NMB = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int rowCount = NMB[0];
            int colCount = NMB[1];
            int blockedCellCount = NMB[2];

            Cell[] blockedCellArr = new Cell[blockedCellCount];

            for (int i = 0; i < blockedCellCount; i++) {
                int[] indexPair = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
                blockedCellArr[i] = new Cell(indexPair[0], indexPair[1]);
            }
            int totalNoOfWays = getTotalNumberOfWays(blockedCellArr, rowCount, colCount);
            writer.write(totalNoOfWays + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getTotalNumberOfWays(Cell[] blockedCellArr, int N, int M) {
        int mod = (int) 1E9 + 7;
        //Dp-Expression => #Ways to reach from Source to (i,j)
        int[][] dpTable = new int[N][M];

        for (int[] row : dpTable) {
            Arrays.fill(row, -1);
        }


        for (Cell blockedCell : blockedCellArr) {
            dpTable[blockedCell.x][blockedCell.y] = 0;
        }

        for (int i = 0; i < N; i++) {
            if (dpTable[i][0] == 0) {
                while (i < N) {
                    dpTable[i++][0] = 0;
                }
            } else {
                dpTable[i][0] = 1;
            }
        }

        for (int j = 0; j < M; j++) {
            if (dpTable[0][j] == 0) {
                while (j < M) {
                    dpTable[0][j++] = 0;
                }
            } else {
                dpTable[0][j] = 1;
            }
        }


        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {

                if (dpTable[i][j] != 0) {
                    long possibleWay = (long) dpTable[i - 1][j] + (long) dpTable[i][j - 1] + (long) dpTable[i - 1][j - 1];
                    dpTable[i][j] = (int) (possibleWay % mod);
                }

            }
        }

        return dpTable[N - 1][M - 1];
    }

    private static class Cell {
        int x;
        int y;

        private Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
