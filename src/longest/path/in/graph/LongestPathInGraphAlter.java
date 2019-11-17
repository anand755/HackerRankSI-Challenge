package longest.path.in.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LongestPathInGraphAlter {
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


        while (!queue.isEmpty()) {
            int n = queue.size();

            while (n-- > 0) {
                int u = queue.poll();
                for (int v : graph.get(u)) {
                    if (!visited[v]) {
                        queue.add(v);
                        visited[v] = true;
                    }
                }
                if (queue.size() == 1) {
                    edgeVertex = queue.peek();
                }
            }
        }


        //Doing BFS from one of the edgeVertex

        queue.clear();
        Arrays.fill(visited, false);

        int length = -1;
        queue.add(edgeVertex);


        while (!queue.isEmpty()) {
            int n = queue.size();

            while (n-- > 0) {
                int u = queue.poll();
                for (int v : graph.get(u)) {
                    if (!visited[v]) {
                        queue.add(v);
                        visited[v] = true;
                    }
                }
            }
            length++;

        }


        return length;
    }
}
