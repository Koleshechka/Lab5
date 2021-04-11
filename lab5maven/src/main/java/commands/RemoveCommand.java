package commands;

import tools.*;

/**
 * Класс-команда remove_by_id.
 * @author Koleshechka
 */
public class RemoveCommand {
    /**
     * Удаляет элемент коллекции с заданным id.
     * @param console
     * @param arg
     */
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
