package is.bst;

import java.io.*;
import java.util.Arrays;

public class IsBSTAlter {
    //Here recursively checking the increasing order of the value in InOrder Traversals
    private static int P;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Node rootNode = buildBinaryTree(new Node(), 0, arrInput, arrLength);
            P = Integer.MIN_VALUE;

            String isBst = isBst(rootNode) ? "True" : "False";
            writer.write(isBst + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static boolean isBst(Node root) {
        if (root == null) {
            return true;
        }
        if (!isBst(root.left)) {
            return false;
        }

        if (root.data > P) {
            P = root.data;
        } else if (root.data < P) {
            return false;
        }

        if (!isBst(root.right)) {
            return false;
        }
        return true;
    }

    private static Node buildBinaryTree(Node root, int index, int[] arrInput, int arrLength) {
        if (index >= arrLength) {
            return null;
        }

        root = new Node(arrInput[index]);
        root.left = buildBinaryTree(root.left, (2 * index) + 1, arrInput, arrLength);
        root.right = buildBinaryTree(root.right, (2 * index) + 2, arrInput, arrLength);

        return root;
    }

    private static class Node {
        Node left;
        Node right;
        int data;

        Node() {

        }

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
