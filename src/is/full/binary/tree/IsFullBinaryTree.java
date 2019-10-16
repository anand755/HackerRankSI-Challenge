package is.full.binary.tree;

import java.io.*;
import java.util.Arrays;

public class IsFullBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Node rootNode = prepareBST(arrInput, arrLength);
            String isFullBinaryTree = isFullBinaryTree(rootNode) ? "True" : "False";
            writer.write(isFullBinaryTree + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static boolean isFullBinaryTree(Node rootNode) {

        if (rootNode == null) {
            return true;
        }

        return (((rootNode.left == null && rootNode.right == null) ||
                (rootNode.left != null && rootNode.right != null)) && isFullBinaryTree(rootNode.left) &&
                isFullBinaryTree(rootNode.right));

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
