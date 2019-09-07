package overlapping.rectangles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OverlapFinal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long testCaseCount = scanner.nextLong();
        scanner.nextLine();
        List<Long> resultList = new ArrayList<>();
        for (long i = 0; i < testCaseCount; i++) {
            String firstRectPos = scanner.nextLine();
            String secondRectPos = scanner.nextLine();

            long A = Long.valueOf(firstRectPos.split("\\s")[0]);
            long B = Long.valueOf(firstRectPos.split("\\s")[1]);
            long C = Long.valueOf(firstRectPos.split("\\s")[2]);
            long D = Long.valueOf(firstRectPos.split("\\s")[3]);

            long E = Long.valueOf(secondRectPos.split("\\s")[0]);
            long F = Long.valueOf(secondRectPos.split("\\s")[1]);
            long G = Long.valueOf(secondRectPos.split("\\s")[2]);
            long H = Long.valueOf(secondRectPos.split("\\s")[3]);

            long area = computeArea(A, B, C, D, E, F, G, H);


            resultList.add(area);
        }
        resultList.forEach(System.out::println);
    }

    private static long overlapArea(long A, long B, long C, long D,
                                    long E, long F, long G, long H) {
        /* Check if there is indeed an overlap.
         * e.g.  E >= C  i.e. the most left point of the rectangle (EFGH) is
         *       on the right side of the most right point of the rectangle (ABCD),
         *       therefore there is no overlapping.
         */
        if ((E >= C) || (F >= D) || (A >= G) || (B >= H))
            return 0;

        /* bottom left point of the overlapping area. */
        long bl_x = Math.max(A, E);
        long bl_y = Math.max(B, F);

        /* top right point of the overlapping area. */
        long tr_x = Math.min(C, G);
        long tr_y = Math.min(D, H);

        return (tr_x - bl_x) * (tr_y - bl_y);
    }

    //Calculating the area of a single rectangle.
    private static long computeArea(long A, long B, long C, long D) {
        return (C - A) * (D - B);
    }

    private static long computeArea(long A, long B, long C, long D,
                                    long E, long F, long G, long H) {
        // The addition of area of the two rectangles minus the overlapping area.
        return computeArea(A, B, C, D) + computeArea(E, F, G, H) -
                overlapArea(A, B, C, D, E, F, G, H);
    }
}
