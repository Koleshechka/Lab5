package run;

import tools.*;
import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        String path = "";
        try{
            path = "/Users/svetlana/Documents/GitHub/Lab5/lab5maven/src/main/java/input.json";
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Console console = new Console(path);
        console.reading(System.in);
    }
}
