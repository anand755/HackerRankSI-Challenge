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
        //We are doing Bottom Up + Post Order Traversals
        int maxAlterNateSum = 0;
        Node root = prepareBst(arrInput, arrLength);

        ValPacket rootValPacket = getMaxVal(root);

        maxAlterNateSum = Math.max(rootValPacket.includeVal, rootValPacket.excludeVal);

        return Math.max(maxAlterNateSum, 0);
    }


    private static ValPacket getMaxVal(Node root) {

        //Assumption: Any given node should return 2 things. 1-> value including that node along with child node
        //2 -> value excluding that node but with child node

        //Main Logic: For a given node
        // includeVal = Max of all the combination for left and right child nodes exclude val + current data
        //excludeVal = Max of all the combination for left and right child nodes include val and
        //leftInclude+rightExclude, rightInclude+leftExclude


        //Base Condition: When node  is null then include value and exclude value will be 0

        if (root == null) {
            return new ValPacket(0, 0);
        }

        ValPacket leftValPacket = getMaxVal(root.left);
        ValPacket rightValPacket = getMaxVal(root.right);

        //taking all the combination and filtering out the max value

        int includeVal_1 = leftValPacket.excludeVal + rightValPacket.excludeVal;
        int includeVal_2 = leftValPacket.excludeVal + root.data;
        int includeVal_3 = rightValPacket.excludeVal + root.data;
        int includeVal_4 = leftValPacket.excludeVal + rightValPacket.excludeVal + root.data;

        int includeVal = Math.max(Math.max(includeVal_1, includeVal_2), Math.max(includeVal_3, includeVal_4));


        int excludeVal_1 = leftValPacket.includeVal;
        int excludeVal_2 = rightValPacket.includeVal;
        int excludeVal_3 = leftValPacket.includeVal + rightValPacket.includeVal;

        int temp_excludeVal_123 = Math.max(Math.max(excludeVal_1, excludeVal_2), excludeVal_3);


        int excludeVal_4 = leftValPacket.includeVal + rightValPacket.excludeVal;
        int excludeVal_5 = leftValPacket.excludeVal + rightValPacket.includeVal;

        int temp_excludeVal_45 = Math.max(excludeVal_4, excludeVal_5);

        int excludeVal = Math.max(temp_excludeVal_123, temp_excludeVal_45);


        return new ValPacket(includeVal, excludeVal);


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

    private static class ValPacket {
        int includeVal;
        int excludeVal;

        private ValPacket(int includeVal, int excludeVal) {
            this.includeVal = includeVal;
            this.excludeVal = excludeVal;
        }
    }
}
