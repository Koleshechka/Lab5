package commands;

import tools.*;

import java.io.InputStream;
import java.util.Scanner;

public class UpdateCommand {
    public static void update(Console console, String arg, Scanner scanner, boolean isFile) {
        try {
            Long id = Long.parseLong(arg);
            if (console.removeId(id)) {
                System.out.println("Элемент с таким id существует в коллекции. Для изменения введите значения его полей.");
                AddCommand.add(console, scanner, id, isFile);
            }
            else System.out.println("В коллекции нет элемента с таким id. Попробуйте еще раз.");
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат аргумента. Попробуйте еще раз.");
        }
    }
}
