package zig.zag.level.order.of.tree;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ZigZagLevelOrderOfTree {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());

            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Node rootNode = prepareBST(arrInput, arrLength);
            printZigZagLevelOrderOfTree(rootNode, writer);
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void printZigZagLevelOrderOfTree(Node rootNode, BufferedWriter writer) throws IOException {
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> tempQueue = new LinkedList<>();
        Stack<Node> tempStack = new Stack<>();

        queue.add(rootNode);
        queue.add(null);

        while (!queue.isEmpty()) {
            Node poppedNode = queue.poll();
            tempQueue.add(poppedNode);

            if (poppedNode == null) {
                if (queue.size() > 0) {
                    queue.add(null);
                }

            } else {
                if (poppedNode.left != null)
                    queue.add(poppedNode.left);
                if (poppedNode.right != null)
                    queue.add(poppedNode.right);
            }
        }
        String order = "rightToLeft";

        //printing level 0
        writer.write(tempQueue.poll().data + " ");
        tempQueue.poll();

        while (!tempQueue.isEmpty()) {
            if (order.equalsIgnoreCase("rightToLeft")) {
                while (tempQueue.peek() != null) {
                    writer.write(tempQueue.poll().data + " ");
                }
                tempQueue.poll();
                order = "leftToRight";
            } else {
                while (tempQueue.peek() != null) {
                    tempStack.push(tempQueue.poll());
                }

                tempQueue.poll();
                order = "rightToLeft";

                while (!tempStack.isEmpty()) {
                    writer.write(tempStack.pop().data + " ");
                }

            }
        }
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
