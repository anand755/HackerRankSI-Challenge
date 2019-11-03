package maximum.xor;

import java.io.*;
import java.util.Arrays;

public class MaximumXor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());

            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int maxXor = getMaxXorVal(arrInput, arrLength);
            writer.write(maxXor + "\n");
            writer.flush();
        }
        //writer.flush();

    }

    private static int getMaxXorVal(int[] arrInput, int arrLength) {
        int arrMaxVal = arrInput[0];
        for (int num : arrInput) {
            arrMaxVal = Math.max(arrMaxVal, num);
        }

        int maxSetBit = (int) (Math.log(arrMaxVal) / Math.log(2));


        Node root = new Node();

        for (int num : arrInput) {
            insertIntoTrie(root, num, maxSetBit);
        }

        int maxXor = 0;

        for (int i = 0; i < arrLength; i++) {
            int val1 = arrInput[i];
            int val2 = getNumberForMaxXor(root, arrInput[i], maxSetBit);
            maxXor = Math.max(maxXor, val1 ^ val2);
        }

        return maxXor;
    }

    private static int getNumberForMaxXor(Node root, int val, int maxSetBit) {
        //This method return the number from trie(root) with which if I do xor of val . it will be maximum.
        //Keep in mind that 10000 > 01111 (Even though only MSB bit is set.. that number is bigger )
        //If the bit is set in val.. then we will look for 0 in the trie so that xor becomes max.
        int ans = 0;
        for (int i = maxSetBit; i >= 0; i--) {
            int bit = ((val >> i) & 1);
            if (root.c[1 - bit] != null) {
                root = root.c[1 - bit];
                ans = ans + ((1 - bit) << i);
            } else {
                root = root.c[bit];
                ans = ans + (bit << i);
            }
        }
        return ans;
    }

    private static void insertIntoTrie(Node root, int num, int maxSetBit) {

        for (int i = maxSetBit; i >= 0; i--) {
            int bit = ((num >> i) & 1);
            if (root.c[bit] == null) {
                root.c[bit] = new Node();
            }
            root = root.c[bit];
        }

    }

    private static class Node {
        Node[] c;

        private Node() {
            this.c = new Node[2];
        }
    }
}
