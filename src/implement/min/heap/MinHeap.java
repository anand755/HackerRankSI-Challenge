package implement.min.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<Integer> list;

    public MinHeap() {
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

    private int getMin() {
        return list.get(1);
    }

    private void delete() {
        int lastIndex = list.size() - 1;

        list.set(1, list.get(lastIndex));
        list.remove(lastIndex);

        int index = 1;
        while (2 * index < list.size()) {

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

    private int getSize() {
        return list.size() - 1;
    }
}
