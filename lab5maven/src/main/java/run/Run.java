package run;

import product.*;
import tools.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tools.Console;
import java.io.FileReader;
import java.util.LinkedList;

public class Run {
    public static void main(String[] args) {
        String path = "";
        try{
            path = "/Users/svetlana/Documents/вт1курс/программирование/lab5maven/src/main/java/input.json";
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        Console console = new Console(path);
        console.read(System.in);

    }
}
