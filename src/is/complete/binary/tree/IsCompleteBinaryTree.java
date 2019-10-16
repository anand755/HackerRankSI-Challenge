package is.complete.binary.tree;

import java.io.*;
import java.util.Arrays;

public class IsCompleteBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Node rootNode = prepareBST(arrInput, arrLength);
            String isCompleteBinaryTree = isCompleteBinaryTree(rootNode, 0, size(rootNode)) ? "Yes" : "No";
            writer.write(isCompleteBinaryTree + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static boolean isCompleteBinaryTree(Node rootNode, int i, int n) {

        if (rootNode == null) {
            return true;
        }

        if ((rootNode.left != null && 2 * i + 1 >= n) || !isCompleteBinaryTree(rootNode.left, 2 * i + 1, n))
            return false;

        if ((rootNode.right != null && 2 * i + 2 >= n) || !isCompleteBinaryTree(rootNode.right, 2 * i + 2, n))
            return false;

        return true;
    }

    private static int size(Node rootNode) {
        if (rootNode == null) {
            return 0;
        }
        return 1 + size(rootNode.left) + size(rootNode.right);
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
