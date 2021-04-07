package commands;

import tools.Console;

import java.io.*;

public class ExecuteCommand {

    public static void execute(Console console, String arg) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(arg);
            console.reading(stream);
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                    System.out.println("Выполнение скрипта закончено.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
