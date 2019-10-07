package stock.span;

import java.io.*;
import java.util.Arrays;

public class StockSpan {
    //TODO this is brute force solution and it got accepted too. want to know any optimized solution if have
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int arrLength = Integer.parseInt(reader.readLine());
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int[] stockSpanArr = getStockSpan(arrInput, arrLength);
            for (int span : stockSpanArr) {
                writer.write(span + " ");
            }
            writer.write("\n");
            //writer.flush();
        }
        writer.flush();
    }

    private static int[] getStockSpan(int[] arrInput, int arrLength) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] countArr = new int[arrLength];

        countArr[0] = 0;
        for (int i = 0; i < arrLength; i++) {
            int number = arrInput[i];
            int count = 0;

            for (int j = i; j >= 0; j--) {
                if (arrInput[j] <= number)
                    count++;
                else
                    break;
            }

            countArr[i] = count;
        }

        return countArr;
    }
}
