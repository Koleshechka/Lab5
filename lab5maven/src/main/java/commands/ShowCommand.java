package commands;

import tools.Console;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Класс-команда show.
 * @author Koleshechka
 */
public class ShowCommand {
    /**
     * Выводит элементы коллекции.
     * @param console
     */
    public static void show(Console console) {
        System.out.println(console.show());
    }
}
