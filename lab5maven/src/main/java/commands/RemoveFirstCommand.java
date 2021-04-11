package commands;

import tools.*;

/**
 * Класс-команда remove_first.
 * @author Koleshechka
 */
public class RemoveFirstCommand {
    /**
     * Удаляет первый элемент коллекции.
     * @param console
     */
    public static void removeFirst(Console console) {
        if (console.removeFirst()) {
            System.out.println("Первый элемент коллекции удален.");
        }
        else System.out.println("В коллекции нет элементов.");
    }
}
