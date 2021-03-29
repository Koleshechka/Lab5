package commands;

import java.util.LinkedList;
import tools.*;

public class InfoCommand {
    public static void info(LinkedList collection) {
        System.out.println("LinkedList<Product> collection, "+", "+Integer.toString(collection.size())+" elements.");
    }
}
