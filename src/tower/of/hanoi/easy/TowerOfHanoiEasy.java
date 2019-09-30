package tower.of.hanoi.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TowerOfHanoiEasy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int numberOfDisk = Integer.parseInt(reader.readLine());

            //from its complexity we can see that T(N) = O(2^N)
            int totalNumberOfMoves = ((1 << numberOfDisk) - 1);

            System.out.println(totalNumberOfMoves);

            printTowerOfHanoi(numberOfDisk, 'A', 'C', 'B');
        }
    }

    private static void printTowerOfHanoi(int numberOfDisk, char src, char dest, char temp) {

        if (numberOfDisk == 0)
            return;
        printTowerOfHanoi(numberOfDisk - 1, src, temp, dest);
        System.out.println("Move " + numberOfDisk + " from " + src + " to " + dest);
        printTowerOfHanoi(numberOfDisk - 1, temp, dest, src);
    }
}
