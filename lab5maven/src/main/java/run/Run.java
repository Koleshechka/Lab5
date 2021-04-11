package run;

import tools.*;



public class Run {
    public static void main(String[] args) {
        String path = "/Users/svetlana/Documents/GitHub/Lab5/lab5maven/src/main/java/input.jso";
        try {
            //String path = args[0];
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Аргумент - имя файла не найден.");
        }
        Console console = new Console(path);
        try {
            console.reading(System.in);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введите аргумент");
        }


    }
}
