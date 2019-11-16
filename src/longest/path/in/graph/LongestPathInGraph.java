package longest.path.in.graph;

import java.io.*;
import java.util.*;

public class LongestPathInGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {

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

            int longestPath = getLongestPath(graph, N);
            writer.write(longestPath + "\n");
            writer.flush();

        }
        //writer.flush();


    }

    private static int getLongestPath(ArrayList<ArrayList<Integer>> graph, int N) {

        //We can consider any node between [1,N]
        int startNode = 1;

        int edgeVertex = 0;

        boolean[] visited = new boolean[N + 1];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();

        queue.add(startNode);
        queue.add(-1);

        while (queue.size() > 1) {

            int u = queue.poll();

            if (u == -1) {
                queue.add(-1);
            } else {
                for (int v : graph.get(u)) {
                    if (!visited[v]) {
                        queue.add(v);
                        visited[v] = true;
                        edgeVertex = v;
                    }
                }
            }
        }

        //Doing BFS from one of the edgeVertex


        queue = new LinkedList<>();
        Arrays.fill(visited, false);

        int length = 0;
        queue.add(edgeVertex);
        queue.add(-1);

        while (queue.size() > 1) {
            int u = queue.poll();

            if (u == -1) {
                length++;
                queue.add(-1);
            } else {
                for (int v : graph.get(u)) {
                    if (!visited[v]) {
                        queue.add(v);
                        visited[v] = true;
                    }
                }
            }
        }


        return length;
    }
}
