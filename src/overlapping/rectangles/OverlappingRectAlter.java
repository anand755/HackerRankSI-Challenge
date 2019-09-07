package overlapping.rectangles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*import static java.lang.Math.max;
import static java.lang.Math.min;*/

class Point {
    long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

public class OverlappingRectAlter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long testCaseCount = scanner.nextLong();
        scanner.nextLine();
        List<Long> resultList = new ArrayList<>();
        for (long i = 0; i < testCaseCount; i++) {
            String firstRectPos = scanner.nextLine();
            String secondRectPos = scanner.nextLine();

            Point l1 = new Point((Long.valueOf(firstRectPos.split("\\s")[0])),
                    (Long.valueOf(firstRectPos.split("\\s")[1])));
            Point r1 = new Point((Long.valueOf(firstRectPos.split("\\s")[2])),
                    (Long.valueOf(firstRectPos.split("\\s")[3])));

            Point l2 = new Point((Long.valueOf(secondRectPos.split("\\s")[0])),
                    (Long.valueOf(secondRectPos.split("\\s")[1])));
            Point r2 = new Point((Long.valueOf(secondRectPos.split("\\s")[2])),
                    (Long.valueOf(secondRectPos.split("\\s")[3])));

            long area1 = Math.abs(l1.x - r1.x) *
                    Math.abs(l1.y - r1.y);

            // Area of 2nd Rectangle
            long area2 = Math.abs(l2.x - r2.x) *
                    Math.abs(l2.y - r2.y);

            long areaI = (Math.min(r1.x, r2.x) -
                    Math.max(l1.x, l2.x)) *
                    (Math.min(r1.y, r2.y) -
                            Math.max(l1.y, l2.y));
            areaI = areaI >= 0 ? areaI : 0;

            resultList.add(area1 + area2 - areaI);
        }
        resultList.forEach(System.out::println);
    }
}
