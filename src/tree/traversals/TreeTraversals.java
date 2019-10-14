package tree.traversals;

import java.io.*;
import java.util.Arrays;

public class TreeTraversals {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Node rootNode = prepareBST(arrInput, arrLength);
            printPreOrderTraversals(rootNode, writer);
            writer.write("\n");
            printInOrderTraversals(rootNode, writer);
            writer.write("\n");
            printPostOrderTraversals(rootNode, writer);
            writer.write("\n\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void printPostOrderTraversals(Node rootNode, BufferedWriter writer) throws IOException {
        if (rootNode == null) {
            return;
        }

        printPostOrderTraversals(rootNode.left, writer);
        printPostOrderTraversals(rootNode.right, writer);
        writer.write(rootNode.data + " ");
    }

    private static void printInOrderTraversals(Node rootNode, BufferedWriter writer) throws IOException {
        if (rootNode == null) {
            return;
        }

        printInOrderTraversals(rootNode.left, writer);
        writer.write(rootNode.data + " ");
        printInOrderTraversals(rootNode.right, writer);
    }

    private static void printPreOrderTraversals(Node rootNode, BufferedWriter writer) throws IOException {
        if (rootNode == null) {
            return;
        }
        writer.write(rootNode.data + " ");
        printPreOrderTraversals(rootNode.left, writer);
        printPreOrderTraversals(rootNode.right, writer);
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
