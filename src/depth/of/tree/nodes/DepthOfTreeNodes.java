package depth.of.tree.nodes;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DepthOfTreeNodes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Node rootNode = prepareBST(arrInput, arrLength);
            printDepthOfNodes(arrInput, rootNode, writer);
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void printDepthOfNodes(int[] arrInput, Node rootNode, BufferedWriter writer) throws IOException {
        Map<Integer, Integer> nodeDepthMap = new HashMap<>();
        for (int nodeVal : arrInput) {
            nodeDepthMap.put(nodeVal, -1);
        }
        getDepth(rootNode, nodeDepthMap, 0);
        for (int node : arrInput) {
            writer.write(nodeDepthMap.get(node) + " ");
        }
    }

    private static void getDepth(Node rootNode, Map<Integer, Integer> nodeDepthMap, int depth) {
        if (rootNode == null) {
            return;
        }
        nodeDepthMap.put(rootNode.data, depth);
        getDepth(rootNode.left, nodeDepthMap, depth + 1);
        getDepth(rootNode.right, nodeDepthMap, depth + 1);
    }

    private static Node prepareBST(int[] arrInput, int arrLength) {
        Node root = new Node(arrInput[0]);
        Node headNode = root;
        for (int i = 1; i < arrLength; i++) {
            root = headNode;

            while (true) {
                if (arrInput[i] <= root.data) {
                    if (root.left == null) {
                        root.left = new Node(arrInput[i]);
                        break;
                    } else
                        root = root.left;

                } else {
                    if (root.right == null) {
                        root.right = new Node(arrInput[i]);
                        break;
                    } else
                        root = root.right;
                }
            }
        }
        return headNode;

    }


    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
