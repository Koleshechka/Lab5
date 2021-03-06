package tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import commands.*;
import product.Product;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс для работы с коллекцией.
 *
 * @author Koleshechka
 */

public class Console {
    private final LinkedList<Product> collection = new LinkedList<>();
    private final HashMap<String, String> map = new HashMap<>();
    private ZonedDateTime collectionDate = ZonedDateTime.now();

    /**
     * Конструктор класса консоли.
     * Создает объект консоли, создает объект парсера для получания значений из json-файла.
     * Получает время создания коллекции.
     * @param path
     */
    public Console(String path) {
        JsonParser jsonParser = new JsonParser(path);
        collection.addAll(jsonParser.reading());
        try {
            LinkedList<ZonedDateTime> dateList = new LinkedList<>();
            for (Product product : collection) {
                dateList.add(product.getCreationDate());
            }
            collectionDate = Collections.min(dateList);
        } catch (Exception e) {
            System.out.println(" ");
        }
    }

    public Console() {
        collectionDate = ZonedDateTime.now();
    }

    public void read(String s, Scanner scanner, boolean isFile) throws IOException {
        String[] commandAll = s.split(" ");
        String command = commandAll[0];
        try {
            switch (command) {
                case "help":
                    HelpCommand.help();
                    break;
                case "info":
                    InfoCommand.info(this);
                    break;
                case "show":
                    ShowCommand.show(this);
                    break;
                case "add":
                    AddCommand.add(this, scanner, null, isFile);
                    break;
                case "update":
                    UpdateCommand.update(this, commandAll[1], scanner, isFile);
                    break;
                case "remove_by_id":
                    RemoveCommand.remove(this, commandAll[1]);
                    break;
                case "clear":
                    ClearCommand.clear(this);
                    break;
                case "exit":
                    ExitCommand.exit();
                    break;
                case "remove_at":
                    RemoveAtCommand.removeAt(this, commandAll[1]);
                    break;
                case "remove_first":
                    RemoveFirstCommand.removeFirst(this);
                    break;
                case "remove_all_by_price":
                    RemovePrice.removePrice(this, commandAll[1]);
                    break;
                case "print_unique_price":
                    PrintPriceCommand.printPrice(this);
                    break;
                case "save":
                    SaveCommand.save(this);
                    break;
                case "print_ascending":
                    PrintAscendingCommand.printAscending(this);
                    break;
                case "execute_script":
                    ExecuteCommand.execute(this, commandAll[1]);
                    break;
                case "sort":
                    SortCommand.sort(this);
                    break;
                default:
                    System.out.println("Неверная команда. Введите help для справки по доступным командам.");
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введите необходимый аргумент.");
        }
    }

    /**
     * Метод добавляет новый элемент в коллекцию.
     * @param product
     */
    public void addToCollection(Product product) {
        this.collection.add(product);
    }

    /**
     * Метод отображение информации о коллекции.
     * @return
     */
    public String info() {
        return "LinkedList <Product> collection, "+ DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(collectionDate)+", "+ collection.size() + " elements.";
    }

    /**
     * Метод отображения коллекции.
     * Возвращает строку с элементами коллекции.
     * @return
     */
    public String show() {
        if (collection.size()==0) {
            System.out.print("В коллекции нет элементов.");
        }
        StringBuilder s = new StringBuilder();
        for (Product product : collection) {
            s.append(product.toString());
        }
        return s.toString();
    }

    /**
     * Метод сортировки коллекции в естественном порядке.
     * @return
     */
    public String productSort() {
        Collections.sort(collection);
        return "Коллекция отсортирована в естественном порядке.";
    }

    /**
     * Метод вывода отсортированной в естественном порядке коллекции.
     * @return
     */
    public String printAscending() {
        Comparator<Product> productComparator = new ProductComparator();
        Collections.sort(collection, productComparator);
        return show();
    }

    /**
     * Удаляет элемент коллекции по его id.
     * @param id
     * @return
     */
    public boolean removeId(long id) {
        int i = 0;
        for (Product product : collection) {
            if (product.getId() == id) {
                return removeIndex(i);
            }
            i++;
        }
        return false;
    }

    /**
     * Удаляет все элементы коллекции.
     */
    public void clear() {
        if (collection.size() != 0) {
            while (collection.size() > 0) {
                removeIndex(0);
            }
            System.out.println("Все элементы коллекции удалены.");
        }
        else System.out.println("В коллекции и так нет элементов.");
    }

    /**
     * Удаляет элемент коллекции по его индексу.
     * @param index
     * @return
     */
    public boolean removeIndex(int index) {
        if (index < collection.size()) {
            collection.get(index).removeByID();
            collection.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Удаляет первый элеменрт коллекции.
     * @return
     */
    public boolean removeFirst(){
        return removeIndex(0);
    }

    /**
     * Удаляет все элементы коллекции, имеющие заданнную цену.
     * @param price
     * @return
     */
    public boolean removePrice(Double price) {
        int i = 0;
        boolean isPrice = false;
        for(Product product : collection) {
            if (product.getPrice().equals(price)) {
                removeIndex(i);
                i--;
                isPrice = true;
            }
            i++;
        }
        return isPrice;
    }

    /**
     * Выводит уникальные значения цены элементов.
     */
    public void getPrice() {

        HashSet<Double> priceSet = new HashSet<>();
        for (Product product : collection) {
            priceSet.add(product.getPrice());
        }
        if (priceSet.size() != 0) {
            System.out.println(priceSet.toString());
        } else System.out.println("В коллекции нет элементов.");
    }

    /**
     * Метод для сохранения коллекции в файл.
     * @throws IOException
     */
    public void writing() throws IOException {
        try {
            OutputStream g = new FileOutputStream("output.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            OutputStreamWriter gg = new OutputStreamWriter(g);
            gg.write(mapper.writeValueAsString(collection));
            gg.close();
            System.out.println("Коллекция сохранена в файл.");
        }catch(Exception e) {
            System.out.println("Невозможно сохранить коллекцию в файл.");
        }
    }

    /**
     * Метод для интерактивного чтения команд.
     * @param stream
     */
    public void reading(InputStream stream) {
        String command;
        Scanner scanner = new Scanner(stream);
        try {
            while (scanner.hasNextLine()) {
                command = scanner.nextLine();
                read(command, scanner, stream != System.in);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
