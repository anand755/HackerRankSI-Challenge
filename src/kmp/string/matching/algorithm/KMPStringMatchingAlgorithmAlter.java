package kmp.string.matching.algorithm;

public class KMPStringMatchingAlgorithmAlter {
    public static void main(String[] args) {
        String str = "AAAABAAAAABBBAAAAB";
        String patt = "AAAB";
        int[] locationArray = new int[str.length()];
        //int locationArray[str.size()];
        int index = 0;
        kmpPattSearch(str, patt, locationArray, index);

        for (int i = 0; i < index; i++) {
            System.out.println("Pattern found at location: " + locationArray[i]);

        }
    }

    private static void kmpPattSearch(String mainString, String pattern, int[] locArray, int loc) {
        int n, m, i = 0, j = 0;
        n = mainString.length();
        m = pattern.length();
        int[] prefixArray = new int[m];    //prefix array as same size of pattern
        findPrefix(pattern, m, prefixArray);
        loc = 0;

        while (i < n) {
            if (mainString.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                locArray[loc] = i - j;      //item found at i-j position.
                loc++;
                j = prefixArray[j - 1];    //get the prefix length from array
            } else if (i < n && pattern.charAt(j) != mainString.charAt(i)) {
                if (j != 0)
                    j = prefixArray[j - 1];
                else
                    i++;
            }

        }

    }

    private static void findPrefix(String pattern, int m, int prefArray[]) {
        int length = 0;
        prefArray[0] = 0;     //first place is always 0 as no prefix

        for (int i = 1; i < m; i++) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                prefArray[i] = length;
            } else {
                if (length != 0) {
                    length = prefArray[length - 1];
                    i--;     //decrease i to avoid effect of increasing after iteration
                } else
                    prefArray[i] = 0;
            }
        }
    }
}