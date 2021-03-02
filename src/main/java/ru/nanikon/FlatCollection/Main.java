package ru.nanikon.FlatCollection;

import ru.nanikon.FlatCollection.arguments.*;
import ru.nanikon.FlatCollection.commands.*;
import ru.nanikon.FlatCollection.exceptions.FileCollectionException;
import ru.nanikon.FlatCollection.exceptions.ScriptException;
import ru.nanikon.FlatCollection.utility.ArgParser;
import ru.nanikon.FlatCollection.utility.CollectionManager;
import ru.nanikon.FlatCollection.utility.JsonLinkedListParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * Main application class. Creates all instances, runs and execute the program.
 * @author Nikonova Natalia
 */

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка! В аргументе вы не указали имя файла. Запустите программу вместе с ним ещё раз.");
            System.exit(0);
        } else if (args.length > 1) {
            System.out.println("Ошибка! Вы указали слишком много аргументов. Запустите программу ещё раз вместе с одним.");
            System.exit(0);
        }
        JsonLinkedListParser parser = new JsonLinkedListParser(args[0]);
        //JsonLinkedListParser parser = new JsonLinkedListParser("exsample.json");
        CollectionManager collectionManager = null;
        try {
            collectionManager = new CollectionManager(parser);
        } catch (FileCollectionException | IOException e) {
            System.out.println(e.getMessage() + ". Проверьте его и запустите программу ещё раз.");
            System.exit(0);
        }
        Stack<Scanner> scannerStack = new Stack<>();
        Stack<Path> pathStack = new Stack<>();
        Deque<String> history = new ArrayDeque<>();
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("history", new HistoryCommand(history));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("exit", new ExitCommand(scannerStack, pathStack));
        commands.put("insert_at", new InsertCommand(collectionManager));
        commands.put("sort", new SortCommand(collectionManager));
        commands.put("remove_any_by_transport", new RemoveAnyByTransportCommand(collectionManager));
        commands.put("average_of_number_of_rooms", new AverageOfNumberOfRoomsCommand(collectionManager));
        commands.put("filter_less_than_view", new FilterLessThanViewCommand(collectionManager));
        commands.put("execute_script", new ExecuteCommand(scannerStack, pathStack));
        commands.put("help", new HelpCommand(commands));
        Scanner scr = new Scanner(System.in);
        offer:
        while (true) {
            String[] line = scr.nextLine().split(" ");
            String nameCommand = line[0];
            int i = 1;
            try {
                if (commands.containsKey(nameCommand)) {
                    Command command = commands.get(nameCommand);
                    AbstractArgument<?>[] listArg = command.getArgs();
                    for (AbstractArgument arg : listArg) {
                        if (arg.isObject()) {
                            ArgParser.parseObject((ObjectArgument<?>) arg, scr, pathStack.isEmpty(), nameCommand.equals("update"));
                        } else if (arg.isEnum()) {
                            ArgParser.parseEnum((EnumArgument<?>) arg, line[i++], scr, pathStack.isEmpty());
                        } else {
                            try {
                                ArgParser.parseLine(arg, line[i++]);
                            } catch (IndexOutOfBoundsException e) {
                                if (!pathStack.isEmpty()) {
                                    throw new ScriptException("Ошибка в скрипте! Введены не все аргументы");
                                }
                                System.out.println("Вы ввели не все аргументы. Попробуйте ещё раз");
                                continue offer;
                            } catch (IOException | NullPointerException | NumberFormatException e) {
                                if (!pathStack.isEmpty()) {
                                    throw new ScriptException("Ошибка в скрипте! " + e.getMessage());
                                }
                                System.out.println(e.getMessage());
                                continue offer;
                            }
                        }
                    }
                    if (i < line.length) {
                        if (!pathStack.isEmpty()) {
                            throw new ScriptException("Ошибка в скрипте! Введено слишком много аргументов!");
                        }
                        System.out.println("Вы ввели слишком много аргументов. Попробуйте ещё раз");
                    } else {
                        if (history.size() == 8) {
                            String s = history.remove();
                        }
                        history.add(nameCommand);
                        scannerStack.push(scr);
                        command.execute(listArg);
                        scr = scannerStack.pop();
                    }
                } else {
                    if (pathStack.isEmpty()) {
                        System.out.println("Команды с именем " + nameCommand + " не существует. Проверьте правильность написания и попробуйте ещё раз.");
                    } else {
                        throw new ScriptException("Ошибка в скрипте: команды с именем " + nameCommand + " не существует.");
                    }
                }
            } catch (ScriptException e) {
                System.out.println(e.getMessage());
                System.out.println("Введите +, если хотите продолжить работу со скриптом, пропустив эту команду, и -, если хотите завершить выполнение скрипта");
                Scanner rightScr = new Scanner(System.in);
                boolean right = false;
                while (!right) {
                    String answer = rightScr.nextLine();
                    if (answer.equals("+")) {
                        right = true;
                    } else if (answer.equals("-")) {
                        right = true;
                        scr.close();
                        scr = scannerStack.pop();
                        Path path = pathStack.pop();
                    } else {
                        System.out.println("Ожидалось +/-, а встречено " + answer);
                    }
                }
            }
            while (!scr.hasNextLine()) {
                scr.close();
                scr = scannerStack.pop();
                Path path = pathStack.pop();
            }
        }
    }
}
