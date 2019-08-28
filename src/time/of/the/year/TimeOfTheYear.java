package time.of.the.year;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeOfTheYear {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());

        long[] inputSeconds = new long[testCaseCount];
        for (int i = 0; i < testCaseCount; i++) {
            long second = Long.parseLong(reader.readLine());
            inputSeconds[i] = second;
        }
        Arrays.stream(inputSeconds).forEach(TimeOfTheYear::calulateDateAndTime);
    }

    private static void calulateDateAndTime(long second) {
        Date date = new Date(second * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy EEEE", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String[] formattedDate = sdf.format(date).split(" ");
        String dateStr = formattedDate[0].toUpperCase();
        String day = formattedDate[1];
        System.out.println(dateStr + " " + day);
    }
}
