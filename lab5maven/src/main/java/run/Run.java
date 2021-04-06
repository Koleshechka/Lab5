package run;

import tools.*;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Run {
    public static void main(String[] args) throws NoSuchFileException{
        String path = "";
        try{
            path = "input.json";
            Console console = new Console(path);
            console.reading(System.in);
        } catch (Exception e) {
            Console console = new Console();
            console.reading(System.in);
            System.out.println(e.toString());
        }
    }
}
