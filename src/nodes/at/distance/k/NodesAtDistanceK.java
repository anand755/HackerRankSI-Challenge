package nodes.at.distance.k;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodesAtDistanceK {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            int[] NSK = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NSK[0];
            int source = NSK[1];
            int distance = NSK[2];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int numberOfNodes = getNumberOfNodesAtDistK(arrInput, arrLength, source, distance);
            writer.write(numberOfNodes + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getNumberOfNodesAtDistK(int[] arrInput, int arrLength, int source, int distance) {

        Node rootNode = prepareBst(arrInput, arrLength);
        Node sourceNode = getSourceNode(rootNode, source);

        List<Node> rootToSourceNodeList = new ArrayList<>();

        putNodesFromRootToSource(rootNode, sourceNode, rootToSourceNodeList);


        int totalCount = nodeCountAtDistance(sourceNode, distance);
        for (int i = 1; i < rootToSourceNodeList.size(); i++) {

            //Checking at right side because the previous node was at left
            if (rootToSourceNodeList.get(i).left == rootToSourceNodeList.get(i - 1)) {
                //Checking if that node itself is at the required distance
                if (distance - i == 0) {
                    totalCount += 1;
                    break;
                } else {
                    totalCount += nodeCountAtDistance(rootToSourceNodeList.get(i).right, distance - i - 1);
                }

            }
            //Checking at left side because the previous node was at right
            else {
                //Checking if that node itself is at the required distance
                if (distance - i == 0) {
                    totalCount += 1;
                    break;
                } else {
                    totalCount += nodeCountAtDistance(rootToSourceNodeList.get(i).left, distance - i - 1);
                }

            }
        }


        return totalCount;
    }

    private static Node getSourceNode(Node root, int source) {
        while (root.data != source) {
            if (source > root.data) {
                root = root.right;
            }
            if (source < root.data) {
                root = root.left;
            }
        }
        return root;
    }


    private static int nodeCountAtDistance(Node root, int distance) {
        if (root == null) {
            return 0;
        }

        if (distance == 0) {
            return 1;
        }
        return (nodeCountAtDistance(root.left, distance - 1) + nodeCountAtDistance(root.right, distance - 1));

    }

    private static boolean putNodesFromRootToSource(Node root, Node source, List<Node> list) {
        if (root == null) {
            return false;
        }

        if (root == source) {
            list.add(root);
            return true;
        }

        if (putNodesFromRootToSource(root.left, source, list)) {
            list.add(root);
            return true;
        }
        if (putNodesFromRootToSource(root.right, source, list)) {
            list.add(root);
            return true;
        }

        return false;
    }

    private static Node prepareBst(int[] arrInput, int arrLength) {
        Node rootNode = new Node(arrInput[0]);

        for (int i = 1; i < arrLength; i++) {
            insertNode(rootNode, arrInput[i]);
        }
        return rootNode;
    }

    private static Node insertNode(Node root, int value) {
        if (root == null)
            return new Node(value);
        if (value > root.data) {
            root.right = insertNode(root.right, value);
        }

        if (value < root.data) {
            root.left = insertNode(root.left, value);
        }
        return root;
    }


    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
