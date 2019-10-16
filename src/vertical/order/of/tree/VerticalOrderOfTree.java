package vertical.order.of.tree;

import java.io.*;
import java.util.*;

public class VerticalOrderOfTree {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Node rootNode = prepareBST(arrInput, arrLength);
            printVerticalOrderOfTree(rootNode, writer);
            writer.write("\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static void printVerticalOrderOfTree(Node rootNode, BufferedWriter writer) throws IOException {
        Map<Integer, List<Integer>> sideMap = new TreeMap<>();

        fillVerticalSideOfTree(rootNode, sideMap, 0);

        for (Map.Entry<Integer, List<Integer>> entry : sideMap.entrySet()) {

            List<Integer> numberList = entry.getValue();
            for (int num : numberList)
                writer.write(num + " ");
            writer.write("\n");
        }
    }


    private static void fillVerticalSideOfTree(Node rootNode, Map<Integer, List<Integer>> sideMap, int side) {
        if (rootNode == null) {
            return;
        }

        fillVerticalSideOfTree(rootNode.left, sideMap, side - 1);
        List<Integer> numberList = sideMap.getOrDefault(side, new ArrayList<>());
        numberList.add(rootNode.data);
        sideMap.put(side, numberList);
        fillVerticalSideOfTree(rootNode.right, sideMap, side + 1);
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
