package commands;

import tools.Console;

import java.io.*;
import java.util.HashSet;

public class ExecuteCommand {

    static HashSet<String> fileNames = new HashSet<>();

    public static void execute(Console console, String arg) throws IOException {
        if (fileNames.contains(arg)) {
            throw new IOException("Не надо строить циклы из script файлов!");
        }
        InputStream stream = new FileInputStream(arg);
        fileNames.add(arg);
        console.reading(stream);
        fileNames.remove(arg);
        System.out.println("Чтение script файла окончено: имя файла = " + arg);
        stream.close();
    }
}
