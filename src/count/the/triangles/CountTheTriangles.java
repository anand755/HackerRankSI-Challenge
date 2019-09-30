package count.the.triangles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CountTheTriangles {
    //TODO THIS IS BRUTE FORCE SOLUTION... GETTING TIME OUT
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int noOfRods = Integer.parseInt(reader.readLine());
            int[] rodsArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int noOfTriangles = getNoOfTriangles(rodsArr, noOfRods);
            System.out.println(noOfTriangles);
        }
    }

    private static int getNoOfTriangles(int[] rodsArr, int noOfRods) {

        int triangleCount = 0;
        for (int i = 0; i < noOfRods; i++) {
            for (int j = i + 1; j < noOfRods; j++) {
                for (int k = j + 1; k < noOfRods; k++) {
                    if (canTriangleBeFormed(rodsArr[i], rodsArr[j], rodsArr[k])) {
                        triangleCount++;
                    }
                }
            }
        }

        return triangleCount;
    }

    private static boolean canTriangleBeFormed(int a, int b, int c) {
        return ((a + b > c) && ((b + c) > a) && ((a + c) > b));
    }
}
