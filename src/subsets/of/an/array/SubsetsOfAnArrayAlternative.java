package subsets.of.an.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class SubsetsOfAnArrayAlternative {
    public static void main(String[] args) throws IOException {

        /*List<Integer> iList = new ArrayList<>();
        iList.add(5);
        iList.add(15);
        iList.add(3);
        Collections.sort(iList);
        printSubset(iList, 3, -1, new ArrayList<>());*/
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());


        while (testCaseCount-- > 0) {
            List<Integer> intList = new ArrayList<Integer>();
            int listSize = Integer.parseInt(reader.readLine());

            int[] arr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr);
            Arrays.stream(arr).forEach(__ -> intList.add(__));
            printSubset(intList, listSize, -1, new ArrayList<>());
        }


    }

    private static void printSubset(List<Integer> initialList, int n, int startIndex, List<Integer> currList) {

        currList.forEach(__ -> System.out.print(__ + " "));
        System.out.println();
        for (int i = startIndex + 1; i < n; i++) {
            currList.add(initialList.get(i));
            printSubset(initialList, n, i, currList);
            currList.remove(currList.size() - 1);
        }
    }


}
