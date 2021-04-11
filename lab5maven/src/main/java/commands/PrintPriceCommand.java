package commands;

import tools.Console;

/**
 * Класс-команда print_unique_price.
 * @author Koleshcehka
 */
public class PrintPriceCommand {
    /**
     * Выводит уникальные значения поля price всех элементов коллекции.
     * @param console
     */
    public static void printPrice(Console console) {
        console.getPrice();
    }
}
