package tools;

import product.*;
import commands.*;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class Console {
    LinkedList <Product> collection = new LinkedList<>();
    private final JsonParser jsonParser;

    public Console(String path) {
        jsonParser = new JsonParser(path);
        collection.addAll(jsonParser.reading());
        //пройдемся и найдем раннюю дату
    }


    public void read(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        while(scanner.hasNext()) {
            String command = scanner.nextLine();
            switch(command) {
                case "help": HelpCommand.help();break;
                case "info": InfoCommand.info();break;
                default: System.out.println("Неверная команда. Введите help для справки по доступным командам.");
            }


        }
    }

    public void addToCollection(Product product) {
        this.collection.add(product);
    }



}
