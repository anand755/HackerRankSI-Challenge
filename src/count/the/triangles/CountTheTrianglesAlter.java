package count.the.triangles;

import java.io.*;
import java.util.*;

public class CountTheTrianglesAlter {

    //This is My solution which was giving wrong solution

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int noOfRods = Integer.parseInt(reader.readLine());

            Map<Integer, List<Integer>> rodsMap = new HashMap<>();

            int[] rodsArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(rodsArr);

            for (int r = 0; r < noOfRods; r++) {
                if (rodsMap.containsKey(rodsArr[r])) {
                    List<Integer> indexList = rodsMap.get(rodsArr[r]);
                    indexList.add(r);
                    rodsMap.put(rodsArr[r], indexList);
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(r);
                    rodsMap.put(rodsArr[r], newList);
                }
            }
            int noOfTriangles = getNoOfTriangles(rodsArr, rodsMap, noOfRods);
            writer.write(noOfTriangles + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getNoOfTriangles(int[] rodsArr, Map<Integer, List<Integer>> rodsMap, int noOfRods) {


        int triangleCount = 0;
        for (int i = 0; i < noOfRods - 2; i++) {
            for (int j = i + 1; j < noOfRods - 1; j++) {
                int maxLength = rodsArr[i] + rodsArr[j] - 1;
                int minLength = rodsArr[j] - rodsArr[i] + 1;

                int count;
                if (maxLength == minLength) {
                    //Search for Isosceles triangle (two side same)
                    count = getFrequencyCount(rodsMap, j + 1, maxLength);

                } else {
                    int ceilIndex = findCeil(rodsArr, j + 1, minLength);
                    int floorIndex = findFloor(rodsArr, j + 1, maxLength);

                    //In case of no triangle formed ceil will return index value then count will become negative. to overcome this using max
                    count = Math.max((floorIndex - ceilIndex + 1), 0);
                }



                /*System.out.println("a = " + rodsArr[i] + " b = " + rodsArr[j] +
                        " Max = " + maxLength + " Max Floor (index) = " + floorIndex +
                        " Min = " + minLength + " Min Ceil (index) = " + ceilIndex +
                        " Count = " + count);*/

                triangleCount += count;
            }
        }

        return triangleCount;
    }

    private static int getFrequencyCount(Map<Integer, List<Integer>> rodsMap, int startIndex, int number) {
        //startIndex inclusive
        int count = 0;
        List<Integer> indexList = rodsMap.get(number);
        for (int index : indexList) {
            count += (index >= startIndex) ? 1 : 0;
        }

        return count;
    }


    private static int findFloor(int[] arrInput, int startIndex, int number) {
        //startIndex inclusive

        int lo = startIndex, hi = arrInput.length - 1;
        int floorIndex = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (arrInput[mid] == number) {
                floorIndex = mid;
                break;
            } else if (arrInput[mid] < number) {
                lo = mid + 1;
                floorIndex = mid;
            } else {
                hi = mid - 1;
            }

        }
        return floorIndex;
    }

    private static int findCeil(int[] arrInput, int startIndex, int number) {
        //startIndex inclusive

        int lo = startIndex, hi = arrInput.length - 1;
        int ceilIndex = arrInput.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arrInput[mid] == number) {
                ceilIndex = mid;
                break;
            } else if (arrInput[mid] > number) {
                hi = mid - 1;
                ceilIndex = mid;
            } else {
                lo = mid + 1;
            }
        }
        return ceilIndex;
    }
}
