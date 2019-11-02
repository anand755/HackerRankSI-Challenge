package falling.football;

import java.io.*;
import java.util.*;

public class FallingFootball {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        List<String> list1;

        for (int caseNo = 1; caseNo <= testCaseCount; caseNo++) {
            int footballCount = Integer.parseInt(reader.readLine());
            int[] footballIndexArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            writer.write("Case " + caseNo + ":\n");
            //list1 = new ArrayList<>();

            getFootballPos(footballIndexArr, writer);

            writer.flush();
        }
        //writer.flush();
    }

    private static void getFootballPos(int[] footballIndexArr, BufferedWriter writer) throws IOException {


        TreeMap<Integer, Character> layer = new TreeMap<>();

        for (int i = -50; i <= 50; i++) {
            layer.put(i, '.');
        }


        for (int index : footballIndexArr) {
            if (layer.get(index) == 'O' && layer.get(index - 1) == 'O' && layer.get(index + 1) == 'O') {
                //add new layer

            } else if (layer.get(index) == 'O' && layer.get(index - 1) == 'O' && layer.get(index + 1) == '.') {
                layer.put(index + 1, 'O');
                //layer.set(index + 1, new Place(index + 1, 'O'));

            } else if (layer.get(index) == 'O' && layer.get(index - 1) == '.' && layer.get(index + 1) == 'O') {
                layer.put(index - 1, 'O');

            } else if (layer.get(index) == 'O' && layer.get(index - 1) == '.' && layer.get(index + 1) == '.') {
                layer.put(index + 1, 'O');

            } else if (layer.get(index) == '.') {
                layer.put(index, 'O');
            }
        }

        int startIndex = layer.firstKey();


        for (Map.Entry<Integer, Character> entry : layer.entrySet()) {
            while (startIndex < entry.getKey()) {
                writer.write(".");
                startIndex++;
            }
            writer.write(entry.getValue() + "");
            startIndex++;

        }


    }
}
