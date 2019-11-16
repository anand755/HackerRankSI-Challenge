package forest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Forest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            int[] NodesEdges = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int N = NodesEdges[0];
            int M = NodesEdges[1];

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }


            for (int i = 0; i < M; i++) {
                int[] uv = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
                int u = uv[0];
                int v = uv[1];

                graph.get(u).add(v);
                graph.get(v).add(u);

            }

            String isForest = isForest(graph, N);
            writer.write(isForest + "\n");

            //writer.flush();

        }
        writer.flush();

    }

    private static String isForest(ArrayList<ArrayList<Integer>> graph, int N) {

        int treeCount = 0;
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(visited, false);

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                treeCount++;
                DFS(graph, i, visited);
            }
        }

        String isForest = treeCount > 1 ? "Yes" : "No";
        return isForest;
    }

    private static void DFS(ArrayList<ArrayList<Integer>> graph, int S, boolean[] visited) {

        visited[S] = true;
        for (int v : graph.get(S)) {
            if (!visited[v]) {
                DFS(graph, v, visited);
            }
        }
    }
}
