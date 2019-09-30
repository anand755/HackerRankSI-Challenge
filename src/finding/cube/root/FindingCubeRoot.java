package finding.cube.root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindingCubeRoot {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            long number = Long.parseLong(reader.readLine());
            long ans = getCubeRoot(number);
            System.out.println(ans);
        }
    }

    private static long getCubeRoot(long number) {
        long lo, hi;
        if (number < 0) {
            lo = number;
            hi = 0;
        } else {
            lo = 0;
            hi = number;
        }
        long cubeRoot = 0L;
        if ((number == 1) || (number == 0) || (number == -1)) {
            cubeRoot = number;
        } else {
            while (lo <= hi) {
                long mid = (lo + hi) / 2;

                if (mid > (number / mid) / mid) {
                    hi = mid - 1;
                }
                if (mid < (number / mid) / mid) {
                    lo = mid + 1;
                }
                if (mid == (number / mid) / mid) {
                    cubeRoot = mid;
                    break;
                }
            }
        }
        return cubeRoot;
    }
}
