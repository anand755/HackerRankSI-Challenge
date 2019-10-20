package number.of.bsts;

import java.io.*;
import java.util.Arrays;

public class NumberOfBSTs {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int bstCount = getBstCount(arrInput, arrLength);
            writer.write(bstCount + "\n");
            writer.flush();
        }
        writer.flush();
    }

    private static int getBstCount(int[] arrInput, int arrLength) {

        count = 0;

        Node rootNode = prepareCbt(0, arrInput, arrLength);

        //Here updating all the count
        countBST(rootNode);

        return count;
    }

    private static void countBST(Node root) {
        if (root == null) {
            return;
        }

        //If it has reached the leaf node then increasing the count;
        if (root.left == null && root.right == null) {
            count += 1;
            return;
        }

        //If the root node is bst then increasing the count with the child node couts
        if (isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            count += getNodeCount(root);
        } else {
            //If it is not leaf node and the the current node is also not bst then checking at left and right
            countBST(root.left);
            countBST(root.right);
        }
    }


    private static int getNodeCount(Node root) {
        if (root == null) {
            return 0;
        }

        return (1 + getNodeCount(root.left) + getNodeCount(root.right));
    }

    private static boolean isBst(Node root, int minVal, int maxVal) {

        if (root == null)
            return true;

        return ((root.data > minVal && root.data < maxVal) && (isBst(root.left, minVal, root.data))
                && (isBst(root.right, root.data, maxVal)));
    }

    private static Node prepareCbt(int index, int[] arrInput, int arrLength) {
        if (index >= arrLength) {
            return null;
        }

        Node root = new Node(arrInput[index]);
        root.left = prepareCbt((2 * index) + 1, arrInput, arrLength);
        root.right = prepareCbt((2 * index) + 2, arrInput, arrLength);

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
