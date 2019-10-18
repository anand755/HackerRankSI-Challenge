package is.bst;

import java.io.*;
import java.util.Arrays;

public class IsBST {
    //Here recursively checking if the value of the data is in the expected range or not
    //This code has been submitted

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Node rootNode = buildBinaryTree(new Node(), 0, arrInput, arrLength);

            String isBst = isBst(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE) ? "True" : "False";
            writer.write(isBst + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static boolean isBst(Node root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }

        return ((root.data > minValue && root.data < maxValue)
                && (isBst(root.left, minValue, root.data)) && (isBst(root.right, root.data, maxValue)));
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
