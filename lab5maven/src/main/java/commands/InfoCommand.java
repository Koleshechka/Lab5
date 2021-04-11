package commands;

import tools.*;

/**
 * Класс-команда Info.
 * @author Koleshechka
 */
public class InfoCommand {
    /**
     * Выводит информацию о коллекции.
     * @param console
     */
    public static void info(Console console) {
        System.out.println(console.info());
    }
}
