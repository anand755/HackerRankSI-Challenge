package prime.fear;

import java.io.*;
import java.util.*;

public class PrimeFear {
    private static boolean[] primeArr = new boolean[(int) 1e6 + 1];

    private static List<Integer> primeFearList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        preparePrimeArray();

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int maxValue = Integer.parseInt(reader.readLine());

            int primeFearCount = getPrimeFearCount(maxValue);

            writer.write(primeFearCount + "\n");
            writer.flush();
        }
        writer.flush();
    }


    private static int getPrimeFearCount(int maxValue) {

        int count = 0;

        for (int number : primeFearList) {
            if (number <= maxValue) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    private static void preparePrimeArray() {
        int maxVal = (int) 1e6;
        //pre populate with true
        for (int i = 0; i <= maxVal; i++) {
            primeArr[i] = true;
        }

        primeArr[0] = false;
        primeArr[1] = false;


        for (int i = 2; i <= (int) 1e3; i++) {
            if (primeArr[i]) {
                for (int j = i * 2; j <= (maxVal / i) + 1; j += i) {
                    primeArr[j] = false;
                }
            }
        }


        //filtering only for prime fear number

        for (int i = 0; i <= maxVal; i++) {
            if (primeArr[i]) {
                if ((i + "").contains("0")) {
                    primeArr[i] = false;
                } else {

                    int div = 10;
                    while ((i % div) != i) {

                        if (!primeArr[i % div]) {
                            primeArr[i] = false;
                            break;
                        }
                        div *= 10;
                    }

                }
                if (primeArr[i]) {
                    primeFearList.add(i);
                }
            }
        }
    }
}
