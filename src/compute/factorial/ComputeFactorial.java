package compute.factorial;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ComputeFactorial {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        Map<Integer, Long> factMap = computeFactMap();
        while (testCaseCount-- > 0) {
            int numInput = Integer.parseInt(reader.readLine());
            long factorial = factMap.get(numInput);
            writer.write(factorial + "\n");
            //writer.flush();
        }
        writer.flush();
    }

    private static Map<Integer, Long> computeFactMap() {
        Map<Integer, Long> factMap = new HashMap<>();

        factMap.put(0, 1L);
        factMap.put(1, 1L);

        long result = 1L;

        int M = (int) 1e9 + 7;

        for (int i = 2; i <= 1000000; i++) {
            result = (result * i) % M;
            factMap.put(i, result);
        }

        return factMap;
    }
}
