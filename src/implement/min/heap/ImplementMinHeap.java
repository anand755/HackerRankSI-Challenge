package implement.min.heap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementMinHeap {
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int operationCount = Integer.parseInt(reader.readLine());

        list.add(null);

        while (operationCount-- > 0) {
            String operation = reader.readLine().trim();
            String outPut = performOperation(operation);
            writer.write(outPut + "");
            writer.flush();
        }
        //writer.flush();
    }

    private static String performOperation(String operation) {

        if (operation.contains("insert")) {

            int data = Integer.parseInt(operation.split("\\s")[1].trim());
            insertIntoMinHeap(data);

        } else if (operation.contains("getMin")) {
            return getMinFromHeap() + "\n";

        } else if (operation.contains("delMin")) {
            deleteFromHeap();
        }

        return "";
    }

    private static void insertIntoMinHeap(int data) {
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

    private static String getMinFromHeap() {
        if (list.size() == 1) {
            return "Empty";
        } else {
            return list.get(1) + "";
        }
    }

    private static void deleteFromHeap() {
        if (list.size() == 1) {
            return;
        }

        int lastIndex = list.size() - 1;

        list.set(1, list.get(lastIndex));
        list.remove(lastIndex);

        int index = 1;
        while (2 * index < list.size()) {

            int leftChildVal = list.get(2 * index);
            int childMinVal = leftChildVal;

            boolean isRightChildExist = false;
            if ((2 * index) + 1 < list.size()) {

                isRightChildExist = true;

                int rightChildVal = list.get(2 * index + 1);
                childMinVal = Math.min(leftChildVal, rightChildVal);
            }


            //int childMinVal = Math.min(list.get(2 * index), list.get(2 * index + 1));
            if (childMinVal < list.get(index)) {

                //swap
                //int childMinValIndex = list.get(2 * index) < list.get(2 * index + 1) ? 2 * index : 2 * index + 1;
                int childMinValIndex = isRightChildExist && (list.get(2 * index + 1) < list.get(2 * index)) ?
                        2 * index + 1 : 2 * index;


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
