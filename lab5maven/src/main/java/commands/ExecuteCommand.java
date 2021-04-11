package commands;

import tools.Console;

import java.io.*;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class ExecuteCommand {

    static HashSet<String> fileNames = new HashSet<>();

    public static void execute(Console console, String arg) throws IOException, NoSuchElementException {
        if (fileNames.contains(arg)) {
            throw new IOException("Не надо строить циклы из script файлов!");
        }
        try {
            InputStream stream = new FileInputStream(arg);
            fileNames.add(arg);
            console.reading(stream);
            fileNames.remove(arg);
            System.out.println("Чтение script файла окончено: имя файла = " + arg);
            stream.close();
        }catch (NoSuchElementException e) {
            System.out.println("Не найден аргумент - имя script файла.");
        }
    }
}
