package count.the.triangles;

import java.io.*;
import java.util.*;

public class CountTheTriangles {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int noOfRods = Integer.parseInt(reader.readLine());
            int[] rodsArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int noOfTriangles = getNoOfTriangles(rodsArr, noOfRods);
            writer.write(noOfTriangles + "\n");
            //writer.flush();
        }
        writer.flush();
    }

    private static int getNoOfTriangles(int[] rodsArr, int noOfRods) {

        Arrays.sort(rodsArr);

        int triangleCount = 0;
        int i = 0;
        int j = 1;
        while (i < noOfRods - 2) {
            int k = j + 1;
            while ((k < noOfRods) && (rodsArr[i] + rodsArr[j] > rodsArr[k])) {
                k++;
            }
            triangleCount += (k - j - 1);
            j++;
            if (j == noOfRods) {
                i++;
                j = i + 1;
            }
        }

        return triangleCount;
    }
}
