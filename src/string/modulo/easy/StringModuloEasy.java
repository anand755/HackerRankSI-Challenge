package string.modulo.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringModuloEasy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());

        while (testCaseCount-- > 0) {
            long N = Long.parseLong(reader.readLine());
            long P = Long.parseLong(reader.readLine());

            System.out.println(N % P);

        }
    }
}
