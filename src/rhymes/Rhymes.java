package rhymes;

import java.io.*;

public class Rhymes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int wordCount = Integer.parseInt(reader.readLine());
        Node root = new Node(0);
        //String[] wordArr = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            char[] word = reader.readLine().trim().toCharArray();
            insertIntoTrie(root, word);

        }

        int queryCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < queryCount; i++) {

            char[] queryWord = reader.readLine().trim().toCharArray();
            int maxLength = getMaxCommonSuffixLength(root, queryWord);
            writer.write(maxLength + "\n");
            writer.flush();
        }

        writer.flush();
    }


    private static int getMaxCommonSuffixLength(Node root, char[] queryWord) {
        int queryLength = queryWord.length;

        //int ans
        for (int i = queryLength - 1; i >= 0; i--) {
            int ch = queryWord[i];

            if ((root.w[ch - 'a']) != null) {

                root = root.w[ch - 'a'];

                if (i == 0) {
                    return root.length;
                }

            } else {
                return root.length;
            }
        }


        return 0;
    }


    private static void insertIntoTrie(Node root, char[] word) {

        int currLength = word.length;

        for (int i = currLength - 1; i >= 0; i--) {
            char ch = word[i];

            if ((root.w[ch - 'a']) == null) {
                root.w[ch - 'a'] = new Node(currLength);

            } else {
                root.w[ch - 'a'].length = Math.max(root.w[ch - 'a'].length, currLength);
            }
            root = root.w[ch - 'a'];

        }

    }


    private static class Node {
        Node[] w;
        int length;

        private Node(int length) {
            this.w = new Node[26];
            this.length = length;
        }
    }


}
