package overlapping.rectangles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OverlappingRectangles {
    //TODO Need to complete
    public static void main(String[] args) {
        String topRect, bottomRect;
        Scanner scanner = new Scanner(System.in);
        long testCaseCount = scanner.nextLong();
        scanner.nextLine();
        List<Long> resultList = new ArrayList<>();
        for (long i = 0; i < testCaseCount; i++) {
            String firstRectPos = scanner.nextLine();
            String secondRectPos = scanner.nextLine();

            long first_y = Long.valueOf(firstRectPos.split(" ")[1]);
            long second_y = Long.valueOf(secondRectPos.split(" ")[1]);
            if (first_y < second_y) {
                bottomRect = firstRectPos;
                topRect = secondRectPos;
            } else {
                bottomRect = secondRectPos;
                topRect = firstRectPos;
            }
            resultList.add(computeArea(bottomRect, topRect));
        }
        resultList.forEach(System.out::println);
    }

    private static long computeArea(String bottomRect, String topRect) {
        long a1 = Long.valueOf(bottomRect.split(" ")[0]);
        long b1 = Long.valueOf(bottomRect.split(" ")[1]);
        long a2 = Long.valueOf(bottomRect.split(" ")[2]);
        long b2 = Long.valueOf(bottomRect.split(" ")[3]);

        long x1 = Long.valueOf(topRect.split(" ")[0]);
        long y1 = Long.valueOf(topRect.split(" ")[1]);
        long x2 = Long.valueOf(topRect.split(" ")[2]);
        long y2 = Long.valueOf(topRect.split(" ")[3]);

        boolean isVertiOverlapped = false, isHorizonOverlapped = false;
        long overlappedWidthX = 0, overlappedHeightY;
        long overlappedArea = 0, resultArea;

        long heightAB, widthAB, heightXY, widthXY;

        if ((x1 > a1) && (y1 > b1) && (x2 < a2) && (y2 < b2)) {

            overlappedArea = (x2 - x1) * (y2 - y1);
            //Check is subset or not
        } else {

            //Check if vertically overlapped or not
            if (b2 > y1) {
                isVertiOverlapped = true;

                //Check if horizontally overlapped or not
                if (a1 < x1) {
                    isHorizonOverlapped = a2 > x1;
                    overlappedWidthX = a2 - x1;
                } else {
                    isHorizonOverlapped = x2 > a1;
                    overlappedWidthX = x2 - a1;
                }
            }
            //If overlapped find Overlapped area
            if (isVertiOverlapped && isHorizonOverlapped) {
                overlappedHeightY = b2 - y1;
                overlappedArea = overlappedWidthX * overlappedHeightY;
            }
        }
        heightAB = b2 - b1;
        widthAB = a2 - a1;

        heightXY = y2 - y1;
        widthXY = x2 - x1;

        resultArea = (heightAB * widthAB) + (heightXY * widthXY) - overlappedArea;
        return resultArea;
    }
}
