package least.common.ancestor;

import java.io.*;
import java.util.Arrays;

public class LeastCommonAncestor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {

            int[] NQ = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int arrLength = NQ[0];
            int queryCount = NQ[1];
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Node root = prepareBST(arrInput, arrLength);
            while (queryCount-- > 0) {
                int[] UV = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
                int uVal = UV[0];
                int vVal = UV[1];
                Node leastCommonAncestor = getLeastCommonAncestor(root, uVal, vVal);
                writer.write(leastCommonAncestor.data + " ");
                //writer.flush();
            }
            writer.write("\n");
        }
        writer.flush();
    }

    private static Node getLeastCommonAncestor(Node root, int uVal, int vVal) {

        if (uVal < root.data && vVal >= root.data) {
            return root;
        }

        if (uVal < root.data && vVal < root.data) {
            return getLeastCommonAncestor(root.left, uVal, vVal);
        } else if (uVal > root.data && vVal > root.data) {
            return getLeastCommonAncestor(root.right, uVal, vVal);
        }

        return root;
    }


    private static Node prepareBST(int[] arrInput, int arrLength) {

        Node headNode = new Node(arrInput[0]);
        for (int i = 1; i < arrLength; i++) {
            insertNode(headNode, arrInput[i]);
        }

        return headNode;
    }

    private static Node insertNode(Node rootNode, int newValue) {
        if (rootNode == null) {
            return new Node(newValue);
        }
        if (newValue < rootNode.data)
            rootNode.left = insertNode(rootNode.left, newValue);
        else
            rootNode.right = insertNode(rootNode.right, newValue);
        return rootNode;
    }


    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
