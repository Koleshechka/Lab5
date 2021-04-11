package commands;

import tools.Console;

/**
 * Класс-команда remove-at.
 * @author Koleshechka
 */
public class RemoveAtCommand {
    /**
     * Удаляет элемент, аходящийся в заданной позиции.
     * @param console
     * @param arg
     */
    public static void removeAt(Console console, String arg) {
        try {
            int index = Integer.parseInt(arg);
            if (console.removeIndex(index)) {
                System.out.println("Элемент по заданному индексу удален.");
            }
            else System.out.println("В коллекции нет элемента с таким индексом.");
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат аргумента. Попробуйте еще раз.");
        }
    }
}
