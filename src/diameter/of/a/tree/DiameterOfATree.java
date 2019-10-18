package diameter.of.a.tree;

import java.io.*;
import java.util.Arrays;

public class DiameterOfATree {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Node rootNode = prepareBST(arrInput, arrLength);
            setHeight(rootNode);
            int treeDiameter = arrLength == 1 ? 1 : getDiameterOfTree(rootNode) + 2;
            writer.write(treeDiameter + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getDiameterOfTree(Node root) {

        if (root == null) {
            return 0;
        }

        int leftHeight = root.left == null ? -1 : root.left.height;
        int rightHeight = root.right == null ? -1 : root.right.height;

        int leftDm = getDiameterOfTree(root.left);
        int rightDm = getDiameterOfTree(root.right);

        int currDm = leftHeight + rightHeight + 1;
        int leftOrRightDm = Math.max(leftDm, rightDm);

        return Math.max(currDm, leftOrRightDm);

    }


    private static int setHeight(Node root) {
        if (root == null) {
            return -1;
        }
        root.height = 1 + Math.max(setHeight(root.left), setHeight(root.right));
        return root.height;
    }


    private static Node prepareBST(int[] arrInput, int arrLength) {

        Node headNode = new Node(arrInput[0], -1);
        for (int i = 1; i < arrLength; i++) {
            insertNode(headNode, arrInput[i]);
        }

        return headNode;
    }

    private static Node insertNode(Node rootNode, int newValue) {
        if (rootNode == null) {
            return new Node(newValue, -1);
        }
        if (newValue < rootNode.data)
            rootNode.left = insertNode(rootNode.left, newValue);
        else
            rootNode.right = insertNode(rootNode.right, newValue);
        return rootNode;
    }


    private static class Node {
        int data;
        int height;
        Node left;
        Node right;

        Node(int data, int height) {
            this.data = data;
            this.height = height;
            left = null;
            right = null;
        }
    }
}
