package tools;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import product.*;
import commands.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

public class Console {
    private LinkedList <Product> collection = new LinkedList<>();
    private JsonParser jsonParser;
    private HashMap<String, String> map = new HashMap<>();
    private ZonedDateTime collectionDate;

    public Console(String path) {
        jsonParser = new JsonParser(path);
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




    public void read(String s, InputStream stream) throws IOException {
        String[] commandAll = s.split(" ");
        String command = commandAll[0];
        switch(command) {
            case "help": HelpCommand.help();break;
            case "info": InfoCommand.info(this);break;
            case "show": ShowCommand.show(this);break;
            case "add": AddCommand.add(this, stream,null);break;
            case "update": UpdateCommand.update(this, commandAll[1], stream);break;
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
        String s = "LinkedList <Product> collection, "+ collectionDate.toString()+", "+ Integer.toString(collection.size()) + " elements.";
        return s;
    }

    public String show() {
        if (collection.size()==0) {
            System.out.println("В коллекции нет элементов.");
        }
        String s = "";
        for (Product product : collection) {
            s+=product.toString();
        }
        return s;
    }

    public boolean removeId(Long id) {
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
        if (priceSet.size()!=0) {
            System.out.println(priceSet.toString());
        } else System.out.println("В коллекции нет элементов.");
    }



    public void writing() throws IOException{
        OutputStream g = new FileOutputStream("output.json");
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.findAndRegisterModules();

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        OutputStreamWriter gg = new OutputStreamWriter(g);
        mapper.writeValue(writer, collection);
        gg.write(writer.toString());
        gg.close();
        System.out.println("Коллекция сохранена в файл.");
    }
    /*

    public void writing() throws IOException{
        OutputStream g = new FileOutputStream("/Users/svetlana/Documents/GitHub/Lab5/lab5maven/src/main/java/output.json");
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        LinkedList<Probuct> list = new LinkedList<>();
        for (Product product: collection) {
            list.add(new Probuct(product));
        }
        OutputStreamWriter gg = new OutputStreamWriter(g);
        mapper.writeValue(writer, list);
        gg.write(writer.toString());
        gg.close();
    }

     */

    public void reading(InputStream stream) {
        String command;
        Scanner scanner = new Scanner(stream);
        try {
            while (scanner.hasNext()) {
                command = scanner.nextLine();
                read(command, stream);
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void readingScript(InputStream stream) {
        String command;
        Scanner scanner = new Scanner(stream);
        try {
            while (scanner.hasNext()) {
                command = scanner.nextLine();
                if (command.equals("")) throw new Exception("");
                else read(command, stream);
            }
        }catch (Exception e) {
            System.out.println("Неверный файл.");
        }
    }
}
