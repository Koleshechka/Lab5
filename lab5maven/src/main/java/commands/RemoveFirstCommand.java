package commands;

import tools.*;

public class RemoveFirstCommand {
    public static void removeFirst(Console console) {
        if (console.removeFirst()) {
            System.out.println("Первый элемент коллекции удален.");
        }
        else System.out.println("В коллекции нет элементов.");
    }
}
