package activity.selection;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ActivitySelection {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int activityCount = Integer.parseInt(reader.readLine());


            int[] activityStartArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] activityEndArr = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int maxActivityCount = getMaxNonOverlappingActivity(activityStartArr, activityEndArr, activityCount);
            writer.write(maxActivityCount + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getMaxNonOverlappingActivity(int[] activityStartArr, int[] activityEndArr, int activityCount) {

        PriorityQueue<Activity> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x.endTime));

        for (int i = 0; i < activityCount; i++) {
            Activity activity = new Activity(activityStartArr[i], activityEndArr[i]);
            minHeap.add(activity);
        }

        int count = 0;
        int prevEndTime = 0;
        while (!minHeap.isEmpty()) {
            Activity currActivity = minHeap.poll();
            if (prevEndTime <= currActivity.startTime){
                count++;
                prevEndTime = currActivity.endTime;
            }

        }


        return count;
    }

    private static class Activity {
        int startTime;
        int endTime;

        private Activity(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
