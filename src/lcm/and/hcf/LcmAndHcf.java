package lcm.and.hcf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LcmAndHcf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();

        List<Long> numList = new ArrayList<>();

        while (testCaseCount-- > 0) {
            numList.add(scanner.nextLong());
            numList.add(scanner.nextLong());
        }
        for (int i = 0; i < numList.size(); i = i + 2) {
            long lcm = doLcm(numList.get(i), numList.get(i + 1));
            long hcf = doHcf(numList.get(i), numList.get(i + 1));
            System.out.println(lcm + " " + hcf);
        }
    }

    private static long doLcm(long x, long y) {

        long lcm;
        lcm = x * y / doHcf(x, y);
        return lcm;
    }

    private static long doHcf(long x, long y) {
        long a;
        long b;

        a = x > y ? x : y;
        b = x < y ? x : y;

        long r = b;

        while (a % b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }
}
