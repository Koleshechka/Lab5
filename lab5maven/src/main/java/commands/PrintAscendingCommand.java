package commands;

import tools.Console;

/**
 * Класс-команда print_ascending.
 * @author Koleshechka
 */
public class PrintAscendingCommand {
    /**
     * Выводит элементы коллекции в порядке возрастания.
     * @param console
     */
    public static void printAscending(Console console) {
        System.out.println(console.show());
    }
}
