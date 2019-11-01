package falling.football;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FallingFootball {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        List<String> list1;

        for (int caseNo = 1; caseNo <= testCaseCount; caseNo++) {
            int footballCount = Integer.parseInt(reader.readLine());
            int[] footballPosArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            writer.write("Case " + caseNo + ":\n");
            list1 = new ArrayList<>();

            getFootballPos(footballPosArr, list1, writer);

            writer.flush();
        }
        //writer.flush();
    }

    private static void getFootballPos(int[] footballPosArr, List<String> list1, BufferedWriter writer) {








    }
}
