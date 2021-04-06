package commands;

import tools.*;

import java.io.InputStream;

public class UpdateCommand {
    public static void update(Console console, String arg, InputStream stream) {
        try {
            Long id = Long.parseLong(arg);
            if (console.removeId(id)==true) {
                System.out.println("Элемент с таким id существует в коллекции. Для изменения введите значения его полей.");
                AddCommand addCommand = new AddCommand();
                addCommand.add(console, stream, id);
            }
            else System.out.println("В коллекции нет элемента с таким id. Попробуйте еще раз.");
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат аргумента. Попробуйте еще раз.");
        }
    }
}
