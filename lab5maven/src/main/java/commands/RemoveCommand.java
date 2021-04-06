package commands;

import tools.*;

public class RemoveCommand {
    public static void remove(Console console, String arg){
        try {
            Long id = Long.parseLong(arg);
            if (console.removeId(id)) {
                System.out.println("Элемент по заданному id удален.");
            }
            else System.out.println("В коллекции нет элемента с таким id.");
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат аргумента. Попробуйте еще раз.");
        }
    }
}
