package run;

import tools.*;

public class Run {
    public static void main(String[] args) {
        String path = "";
        try{
            path = "/Users/svetlana/Documents/GitHub/Lab5/lab5maven/src/main/java/input.json";
            //path = args[0];
            Console console = new Console(path);
            console.reading(System.in);
        } catch (Exception e) {
            Console console = new Console();
            console.reading(System.in);
            System.out.println(e.toString());
        }
    }
}
