package commands;

import tools.Console;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class ExecuteCommand {

    public static void execute(Console console, String arg) {
        try {
            File a = new File(arg);
            InputStream stream = new FileInputStream(a);
            console.readingScript(stream);
        }catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}
