package changing.directories;

import java.io.*;
import java.util.Stack;

public class ChangingDirectories {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            Stack<String> directoryStack = new Stack<>();

            int commandsCount = Integer.parseInt(reader.readLine());
            while (commandsCount-- > 0) {
                String commandStr = reader.readLine().trim();
                String result = executeCommand(directoryStack, commandStr);

                writer.write(result);
                writer.flush();
            }
            writer.write("\n");
        }
        //writer.flush();
    }

    private static String executeCommand(Stack<String> directoryStack, String commandStr) {
        String pwdString = "";

        String commandType = commandStr.contains("cd") ? "cd" : "pwd";
        if (commandType.equalsIgnoreCase("cd")) {
            String rawPathStr = commandStr.substring(2).trim();

            if (rawPathStr.equals("/")) {
                //cd /
                directoryStack.clear();
            } else if (rawPathStr.startsWith("/")) {
                // cd /sem/labs
                String[] dirs = rawPathStr.substring(1).split("/");
                directoryStack.clear();
                //directoryStack.empty();
                for (String dir : dirs) {
                    if (dir.equals("..")) {
                        if (!directoryStack.isEmpty())
                            directoryStack.pop();
                    } else
                        directoryStack.push(dir);
                }
            } else {
                if (rawPathStr.contains("/")) {
                    // cd ../../os/labs/../slides
                    //cd os/labs

                    String[] dirs = rawPathStr.split("/");
                    for (String dir : dirs) {
                        if (dir.equals("..")) {
                            if (!directoryStack.isEmpty())
                                directoryStack.pop();
                        } else {
                            directoryStack.push(dir);
                        }
                    }
                } else {
                    //cd os
                    //cd ..
                    if (rawPathStr.equals("..")) {
                        if (!directoryStack.isEmpty())
                            directoryStack.pop();
                    } else {
                        directoryStack.push(rawPathStr);
                    }
                }
            }
        } else {
            //pwd

            if (directoryStack.isEmpty()) {
                pwdString = "/";
            } else {
                pwdString = "/";
                for (String dir : directoryStack) {

                    pwdString = pwdString + dir + "/";
                }
            }
            pwdString += "\n";
        }
        return pwdString;
    }
}
