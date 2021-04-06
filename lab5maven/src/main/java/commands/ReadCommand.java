package commands;

import java.io.InputStream;
import java.util.Scanner;

public class ReadCommand {
    public static String readForAdd(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        return scanner.nextLine();
    }
}
