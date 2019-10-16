package sum.of.numbers.from.root.to.leaf.paths;

import java.io.*;
import java.util.Arrays;

public class SumOfNumbersFromRootToLeafPaths {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = (int) 1e9 + 7;
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Node rootNode = prepareBST(arrInput, arrLength);
            long sum = getSumOFNumbersFromRootToLeaf(rootNode, M, 0);
            writer.write(sum + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static long getSumOFNumbersFromRootToLeaf(Node rootNode, int M, long val) {
        if (rootNode == null)
            return 0L;


        val = ((val * (int) Math.pow(10, digitLength(rootNode.data))) % M + rootNode.data) % M;

        if (rootNode.left == null && rootNode.right == null) {
            return val;
        }

        return (getSumOFNumbersFromRootToLeaf(rootNode.left, M, val) +
                getSumOFNumbersFromRootToLeaf(rootNode.right, M, val)) % M;
    }

    private static int digitLength(int val) {
        String valAsString = val + "";
        return valAsString.trim().length();
    }

    private static Node prepareBST(int[] arrInput, int arrLength) {

        Node headNode = new Node(arrInput[0]);
        for (int i = 1; i < arrLength; i++) {
            insertNode(headNode, arrInput[i]);
        }

        return headNode;
    }

    private static Node insertNode(Node rootNode, int newValue) {
        if (rootNode == null) {
            return new Node(newValue);
        }
        if (newValue < rootNode.data)
            rootNode.left = insertNode(rootNode.left, newValue);
        else
            rootNode.right = insertNode(rootNode.right, newValue);
        return rootNode;
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
