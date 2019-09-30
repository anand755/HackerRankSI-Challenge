package express.as.a.power.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExpressAsAPowerB {
    //TODO NEED TO COMPLETE
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-->0){
            int number = Integer.parseInt(reader.readLine());
            checkIfPower(number);
        }
    }

    private static void checkIfPower(int number) {
        for (int i=2; i<=Math.sqrt(number); i++){
            //int ans = Math.log(number)/Math.log(i);
        }
    }
}
