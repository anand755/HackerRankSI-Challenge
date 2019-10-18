package pre.order.in.order.to.post.order;

import java.io.*;
import java.util.Arrays;

public class PreOrderInOrderToPostOrder {
    private static int preIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] preOrder_dlr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] inOrder_ldr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            printPostOrder(preOrder_dlr, inOrder_ldr, arrLength, writer);
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void printPostOrder(int[] preOrder_dlr, int[] inOrder_ldr, int arrLength, BufferedWriter writer) throws IOException {

        preIndex = 0;

        Node headNode = buildTree(inOrder_ldr, preOrder_dlr, 0, arrLength - 1);

        printPostOrderTraversals(headNode, writer);
    }

    private static void printPostOrderTraversals(Node rootNode, BufferedWriter writer) throws IOException {
        if (rootNode == null) {
            return;
        }

        printPostOrderTraversals(rootNode.left, writer);
        printPostOrderTraversals(rootNode.right, writer);
        writer.write(rootNode.data + " ");
    }

    private static Node buildTree(int[] inOrder, int[] preOrder, int inStart, int inEnd) {

        if (inStart > inEnd)
            return null;

        Node root = new Node(preOrder[preIndex++]);


        int inIndex = getIndex(inOrder, inStart, inEnd, root.data);
        root.left = buildTree(inOrder, preOrder, inStart, inIndex - 1);
        root.right = buildTree(inOrder, preOrder, inIndex + 1, inEnd);
        return root;
    }

    private static int getIndex(int[] arr, int start, int end, int value) {

        for (int i = start; i <= end; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return 0;
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
