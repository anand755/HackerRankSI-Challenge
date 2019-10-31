package maximum.alternate.nodes.sum;

import java.io.*;
import java.util.Arrays;

public class MaximumAlternateNodesSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int maxAlternativeSum = getMaxAlterNodeSum(arrInput, arrLength);
            writer.write(maxAlternativeSum + "\n");
            writer.flush();
        }
        writer.flush();
    }

    private static int getMaxAlterNodeSum(int[] arrInput, int arrLength) {
        Node root = prepareBst(arrInput, arrLength);


        return 0;
    }

    private static Node prepareBst(int[] arrInput, int arrLength) {
        Node root = new Node(arrInput[0]);

        for (int i = 1; i < arrLength; i++) {
            insertNode(root, arrInput[i]);
        }

        return root;
    }

    private static Node insertNode(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
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

        private Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
