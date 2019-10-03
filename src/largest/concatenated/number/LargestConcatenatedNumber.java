package largest.concatenated.number;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LargestConcatenatedNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrayLength = Integer.parseInt(reader.readLine());
            List<String> listInput = Stream.of(reader.readLine().split("\\s")).collect(Collectors.toList());
            listInput.sort((a, b) -> (b + a).compareTo(a + b));

            listInput.forEach(System.out::print);
            System.out.println();
        }
    }
}
