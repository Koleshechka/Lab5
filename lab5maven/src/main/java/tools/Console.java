package tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import product.*;
import commands.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

public class Console {
    private final LinkedList <Product> collection = new LinkedList<>();
    private final HashMap<String, String> map = new HashMap<>();
    private final ZonedDateTime collectionDate;

    public Console(String path) {
        JsonParser jsonParser = new JsonParser(path);
        collection.addAll(jsonParser.reading());
        LinkedList <ZonedDateTime> dateList = new LinkedList<>();
        for (Product product : collection) {
            dateList.add(product.getCreationDate());
        }
        collectionDate = Collections.min(dateList);

        collection.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getCreationDate().compareTo(o2.getCreationDate());
            }
        });
    }

    public Console() {
        collectionDate = ZonedDateTime.now();
    }




    public void read(String s, Scanner scanner, boolean isFile) throws IOException {
        String[] commandAll = s.split(" ");
        String command = commandAll[0];
        switch(command) {
            case "help": HelpCommand.help();break;
            case "info": InfoCommand.info(this);break;
            case "show": ShowCommand.show(this);break;
            case "add": AddCommand.add(this, scanner,null, isFile);break;
            case "update": UpdateCommand.update(this, commandAll[1], scanner, isFile);break;
            case "remove_by_id": RemoveCommand.remove(this, commandAll[1]);break;
            case "clear": ClearCommand.clear(this);break;
            case "exit": ExitCommand.exit();break;
            case "remove_at": RemoveAtCommand.removeAt(this, commandAll[1]);break;
            case "remove_first": RemoveFirstCommand.removeFirst(this);break;
            case "remove_all_by_price": RemovePrice.removePrice(this, commandAll[1]);break;
            case "print_unique_price": PrintPriceCommand.printPrice(this);break;
            case "save": SaveCommand.save(this);break;
            case "print_ascending": PrintAscendingCommand.printAscending(this);break;
            case "execute_script": ExecuteCommand.execute(this, commandAll[1]);break;
            case "sort": SortCommand.sort(this);break;
            default: System.out.println("Неверная команда. Введите help для справки по доступным командам.");
            }
    }

    public void addToCollection(Product product) {
        this.collection.add(product);
        collection.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getCreationDate().compareTo(o2.getCreationDate());
            }
        });
    }

    public String info() {
        return "LinkedList <Product> collection, "+ collectionDate.toString()+", "+ collection.size() + " elements.";
    }

    public String show() {
        if (collection.size()==0) {
            System.out.println("В коллекции нет элементов.");
        }
        StringBuilder s = new StringBuilder();
        for (Product product : collection) {
            s.append(product.toString());
        }
        return s.toString();
    }

    public boolean removeId(long id) {
        boolean isId = false;
        for (Product product : collection) {
            if (product.getId() == id) {
                collection.remove(product);
                isId = true;
                break;
            }
        }
        return isId;
    }

    public void clear() {
        if (collection.size()!=0) {
            collection.clear();
            System.out.println("Все элементы коллекции удалены.");
        }
        else System.out.println("В коллекции и так нет элементов.");
    }


    public boolean removeIndex(int index) {
        boolean isIndex = false;
        if (index <= collection.size()) {
            collection.remove(index);
            isIndex = true;
        }
        return isIndex;
    }

    public boolean removeFirst(){
        boolean is = false;
        if (collection!= null) {
            collection.removeFirst();
            is = true;
        }
        return is;
    }

    public boolean removePrice(Double price){
        boolean isPrice = false;
        for(Product product : collection) {
            if (product.getPrice().equals(price)) {
                collection.remove(product);
                isPrice = true;
            }
        }
        return isPrice;
    }

    public void getPrice() {

        HashSet<Double> priceSet = new HashSet<>();
        for (Product product : collection) {
            priceSet.add(product.getPrice());
        }
        if (priceSet.size() != 0) {
            System.out.println(priceSet.toString());
        } else System.out.println("В коллекции нет элементов.");
    }


    public void writing() throws IOException {
        OutputStream g = new FileOutputStream("output.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        OutputStreamWriter gg = new OutputStreamWriter(g);
        gg.write(mapper.writeValueAsString(collection));
        gg.close();
        System.out.println("Коллекция сохранена в файл.");
    }



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
