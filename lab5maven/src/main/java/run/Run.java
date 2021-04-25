package run;

import tools.*;

/**
 * Main-class приложения.
 *
 * @author Koleshechka
 */
public class Run {
    public static void main(String[] args) {
        String path ="";
        try {
            path = args[0];
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Аргумент - имя файла не найден.");

        }
        Console console = new Console(path);
        try {
            console.reading(System.in);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введите аргумент");
        }


    }
}
