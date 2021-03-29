package commands;

import java.util.Iterator;
import java.util.LinkedList;

public class ShowCommand {
    public static void show(LinkedList collection) {
        Iterator iterator = collection.iterator();
        while(iterator.hasNext()) {
            try{
                System.out.println(iterator.next().toString());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
