package commands;

import tools.Console;

public class RemovePrice {
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
