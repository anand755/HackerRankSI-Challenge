package find.first.repeating.character;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FindFirstRepeatingCharacter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            String string = reader.readLine();
            char ch = getRepeatingChar(string);
            writer.write(ch + "\n");
            writer.flush();
        }
    }

    private static char getRepeatingChar(String string) {

        Map<Character, Integer> charMap = new HashMap<>();

        for (char ch : string.toCharArray()) {
            if (charMap.containsKey(ch)) {
                int count = charMap.get(ch);
                charMap.put(ch,count+1);
            } else {
                charMap.put(ch, 1);
            }
        }
        for (char ch:string.toCharArray()){
            if (charMap.get(ch)>1){
                return ch;
            }
        }
        return '.';
    }
}
