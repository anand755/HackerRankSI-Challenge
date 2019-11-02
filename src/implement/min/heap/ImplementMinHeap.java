package implement.min.heap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementMinHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int operationCount = Integer.parseInt(reader.readLine());


        MinHeap minHeap = new MinHeap();
        while (operationCount-- > 0) {
            String operation = reader.readLine().trim();
            String outPut = performOperation(operation, minHeap);
            writer.write(outPut + "");
            writer.flush();
        }
        writer.flush();
    }

    private static String performOperation(String operation, MinHeap minHeap) {


        if (operation.contains("insert")) {

            int data = Integer.parseInt(operation.split("\\s")[1].trim());
            minHeap.insert(data);


        } else if (operation.contains("getMin")) {

            return minHeap.getMin() + "\n";

        } else if (operation.contains("delMin")) {
            minHeap.delete();

        }


        return "";
    }


    private static class MinHeap {
        private List<Integer> list;

        private MinHeap() {
            this.list = new ArrayList<>();
            this.list.add(null);
        }

        private void insert(int data) {
            list.add(data);
            int index = list.size() - 1;

            while (index != 1 && list.get(index) < list.get(index / 2)) {

                //swap
                int tempVal = list.get(index);
                list.set(index, list.get(index / 2));
                list.set(index / 2, tempVal);

                index = index / 2;
            }

        }

        private String getMin() {
            if (list.size() == 1) {
                return "Empty";
            } else {
                return list.get(1) + "";
            }

        }

        private void delete() {
            int lastIndex = list.size() - 1;

            list.set(1, list.get(lastIndex));
            list.remove(lastIndex);

            int index = 1;
            while (2 * index + 1 < list.size()) {

                int childMinVal = Math.min(list.get(2 * index), list.get(2 * index + 1));
                if (childMinVal < list.get(index)) {

                    //swap
                    int childMinValIndex = list.get(2 * index) < list.get(2 * index + 1) ? 2 * index : 2 * index + 1;

                    int tempVal = list.get(childMinValIndex);
                    list.set(childMinValIndex, list.get(index));
                    list.set(index, tempVal);

                    index = childMinValIndex;

                } else {
                    break;
                }

            }
        }
    }
}
