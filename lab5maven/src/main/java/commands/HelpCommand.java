package commands;

/**
 * Класс-команда Help.
 * @author Koleshechka
 */
public class HelpCommand {
    /**
     * Выводит справку о доступных командах.
     */
    public static void help() {
        System.out.println("Справка по доступным командам:\n" +
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id :  удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "sort : отсортировать коллекцию в естественном порядке\n" +
                "remove_all_by_price price : удалить из коллекции все элементы, значение поля price которого эквивалентно заданному\n" +
                "print_ascending : вывести элементы коллекции в порядке возрастания\n" +
                "print_unique_price : вывести уникальные значения поля price всех элементов в коллекции");
    }
}
