package commands;

import tools.Console;

/**
 * Класс-команда remove_all_by_price.
 * @author Koleshechka
 */
public class RemovePrice {
    /**
     * Удаляет все элементы коллекции,значения поля price которых эквивалентны заданному.
     * @param console
     * @param arg
     */
    public static void removePrice(Console console, String arg){
        try {
            Double price = Double.parseDouble(arg);
            if (console.removePrice(price)) {
                System.out.println("Элементы с заданной ценой удалены.");
            }
            else System.out.println("В коллекции нет элементов с заданной ценой.");
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат аргумента.");
        }
    }
}
