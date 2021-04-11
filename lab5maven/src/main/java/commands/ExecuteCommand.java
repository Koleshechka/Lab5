package commands;

import tools.Console;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Класс-команда execute_script.
 * @author Koleshechka
 */
public class ExecuteCommand {

    static HashSet<String> fileNames = new HashSet<>();

    /**
     * Выполняет скрипт из переданного файла.
     * @param console
     * @param arg
     * @throws IOException
     * @throws NoSuchElementException
     */
    public static void execute(Console console, String arg) throws IOException, NoSuchElementException {
        if (fileNames.contains(arg)) {
            throw new IOException("Не надо строить циклы из script файлов!");
        }
        try {
            if (arg.equals("/dev/zero") || arg.equals("/dev/random")) {throw new NullPointerException();}
            InputStream stream = new FileInputStream(arg);
            fileNames.add(arg);
            console.reading(stream);
            fileNames.remove(arg);
            System.out.println("Чтение script файла окончено: имя файла = " + arg);
            stream.close();
        }catch (NoSuchElementException e) {
            System.out.println("Не найден аргумент - имя script файла.");
        } catch (NullPointerException e) {
            System.out.println("Введите корректный файл.");
        } catch (Exception e) {
            System.out.println("Файл не найден.");
        }
    }
}
