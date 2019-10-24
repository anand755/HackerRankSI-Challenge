package prime.fear;

import java.io.*;
import java.util.Arrays;

public class PrimeFear {
    private static boolean[] primeArr = new boolean[(int) 1e6 + 1];
    private static int[] primeFearCountArr = new int[(int) 1e6 + 1];


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        preparePrimeFearArray();

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

        //This is O(1) solution
        int count = primeFearCountArr[maxValue];
        return count;
    }

    private static void preparePrimeFearArray() {
        int maxVal = (int) 1e6;
        //pre populating the array with true

        Arrays.fill(primeArr, true);


        //As 0 and 1 is not prime
        primeArr[0] = false;
        primeArr[1] = false;

        for (int i = 2; i <= (int) 1e3; i++) {
            if (primeArr[i]) {
                for (int j = i * 2; j <= maxVal; j += i) {
                    primeArr[j] = false;
                }
            }
        }

        //checking if the number is fear prime then adding the count to primeFearCountArr array such that
        // primeFearCountArr[i] contains prime fear count till i

        int count = 0;

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
                    //incrementing the count only if it fear prime
                    count++;
                }
            }

            //This count array stores number of total fear primes till i
            primeFearCountArr[i] = count;
        }
    }
}
