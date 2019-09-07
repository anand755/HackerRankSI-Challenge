package subsets.of.an.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubsetsOfAnArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            int listSize = Integer.parseInt(reader.readLine());

            List<Integer> inputList = Stream.of(reader.readLine().split("\\s")).map(Integer::valueOf).collect(Collectors.toList());
            Collections.sort(inputList);

            printSubSetOfArray(inputList, listSize, -1, new ArrayList<>());
            System.out.println();
        }
    }

    private static void printSubSetOfArray(List<Integer> initialList, int n, int startIndex, List<Integer> currList) {

        if (!currList.isEmpty()) {
            currList.forEach(__ -> System.out.print(__ + " "));
            System.out.println();
        }
        for (int i = startIndex + 1; i < n; i++) {
            currList.add(initialList.get(i));
            printSubSetOfArray(initialList, n, i, currList);
            currList.remove(currList.size() - 1);
        }
    }
}
