package path.in.a.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PathInAGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        for (int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            int[] NodesEdges = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int N = NodesEdges[0];
            int M = NodesEdges[1];

            //Forming Graph using adjacency list
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

            boolean[] visited = new boolean[N + 1];


            int queryCount = Integer.parseInt(reader.readLine());

            writer.write("Test Case #" + testCaseNo + ":" + "\n");
            while (queryCount-- > 0) {
                int[] SourceDestination = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
                int S = SourceDestination[0];
                int D = SourceDestination[1];
                Arrays.fill(visited, false);

                String isPathExist = DFS(graph, S, D, visited) ? "Yes" : "No";
                writer.write(isPathExist + "\n");
                //writer.flush();
            }
        }
        writer.flush();

    }

    private static boolean DFS(ArrayList<ArrayList<Integer>> graph, int S, int D, boolean[] visited) {

        if (S == D) {
            return true;
        }

        visited[S] = true;

        for (int v : graph.get(S)) {
            if (!visited[v]) {
                if (DFS(graph, v, D, visited)) {
                    return true;
                }
            }
        }
        return false;

    }
}
