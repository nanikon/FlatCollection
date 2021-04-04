package ru.nanikon.FlatCollection;

import ru.nanikon.FlatCollection.arguments.*;
import ru.nanikon.FlatCollection.commands.ExecuteCommand;
import ru.nanikon.FlatCollection.exceptions.FileCollectionException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;
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
    public static String PS1 = "$";
    public static String PS2 = "> ";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка! В аргументе вы не указали имя файла. Запустите программу вместе с ним ещё раз.");
            System.exit(-1);
        } else if (args.length > 1) {
            System.out.println("Ошибка! Вы указали слишком много аргументов. Запустите программу ещё раз вместе с одним.");
            System.exit(-1);
        }
        JsonLinkedListParser parser = new JsonLinkedListParser(args[0]);
        //JsonLinkedListParser parser = new JsonLinkedListParser("empty.json");
        CollectionManager collectionManager = null;
        try {
            collectionManager = new CollectionManager(parser);
        } catch (FileCollectionException | IOException e) {
            System.out.println(e.getMessage() + ". Проверьте его и запустите программу ещё раз.");
            System.exit(-1);
        }
        Stack<Scanner> scannerStack = new Stack<>();
        Stack<Path> pathStack = new Stack<>();
        Deque<String> history = new ArrayDeque<>();
        HashMap<String, ru.nanikon.FlatCollection.commands.Command> commands = new HashMap<>();
        commands.put("history", new ru.nanikon.FlatCollection.commands.HistoryCommand(history));
        commands.put("info", new ru.nanikon.FlatCollection.commands.InfoCommand(collectionManager));
        commands.put("show", new ru.nanikon.FlatCollection.commands.ShowCommand(collectionManager));
        commands.put("add", new ru.nanikon.FlatCollection.commands.AddCommand(collectionManager));
        commands.put("update", new ru.nanikon.FlatCollection.commands.UpdateCommand(collectionManager));
        commands.put("remove_by_id", new ru.nanikon.FlatCollection.commands.RemoveCommand(collectionManager));
        commands.put("clear", new ru.nanikon.FlatCollection.commands.ClearCommand(collectionManager));
        commands.put("save", new ru.nanikon.FlatCollection.commands.SaveCommand(collectionManager));
        commands.put("exit", new ru.nanikon.FlatCollection.commands.ExitCommand(scannerStack, pathStack));
        commands.put("insert_at", new ru.nanikon.FlatCollection.commands.InsertCommand(collectionManager));
        commands.put("sort", new ru.nanikon.FlatCollection.commands.SortCommand(collectionManager));
        commands.put("remove_any_by_transport", new ru.nanikon.FlatCollection.commands.RemoveAnyByTransportCommand(collectionManager));
        commands.put("average_of_number_of_rooms", new ru.nanikon.FlatCollection.commands.AverageOfNumberOfRoomsCommand(collectionManager));
        commands.put("filter_less_than_view", new ru.nanikon.FlatCollection.commands.FilterLessThanViewCommand(collectionManager));
        commands.put("execute_script", new ExecuteCommand(scannerStack, pathStack));
        commands.put("help", new ru.nanikon.FlatCollection.commands.HelpCommand(commands));
        Scanner scr = new Scanner(System.in, "UTF-8");
        offer:
        while (true) {
            if (pathStack.isEmpty()) {
                System.out.print(PS1);
            }
            String[] line = new String[0];
            try {
                line = scr.nextLine().trim().split("[ \t\f]+");
            } catch (NoSuchElementException e) {
                System.out.println("Вы завершили выполнение программы");
                System.exit(0);
            }
            String nameCommand = line[0];
            int i = 1;
            try {
                if (commands.containsKey(nameCommand)) {
                    ru.nanikon.FlatCollection.commands.Command command = commands.get(nameCommand);
                    AbstractArgument<?>[] listArg = command.getArgs();
                    for (AbstractArgument arg : listArg) {
                        if (arg.isObject()) {
                            if (i < line.length) {
                                if (!pathStack.isEmpty()) {
                                    throw new ScriptException("Ошибка в скрипте! Введено слишком много аргументов!");
                                }
                                System.out.println("Вы ввели слишком много аргументов. Попробуйте ещё раз");
                                continue offer;
                            }
                            ArgParser.parseObject((ObjectArgument<?>) arg, scr, pathStack.isEmpty(), nameCommand.equals("update"));
                        } else if (arg.isEnum()) {
                            try {
                                ArgParser.parseEnum((EnumArgument<?>) arg, line[i++], scr, pathStack.isEmpty());
                            } catch (IndexOutOfBoundsException e) {
                                if (!pathStack.isEmpty()) {
                                    throw new ScriptException("Ошибка в скрипте! Введены не все аргументы");
                                }
                                System.out.println("Вы ввели не все аргументы. Попробуйте ещё раз");
                                continue offer;
                            }
                        } else {
                            try {
                                ArgParser.parseLine(arg, line[i++]);
                            } catch (IndexOutOfBoundsException e) {
                                if (!pathStack.isEmpty()) {
                                    throw new ScriptException("Ошибка в скрипте! Введены не все аргументы");
                                }
                                System.out.println("Вы ввели не все аргументы. Попробуйте ещё раз");
                                continue offer;
                            } catch (IOException | NullPointerException | NumberFormatException | NotPositiveNumberException e) {
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
                        String result = command.execute(listArg);
                        System.out.println(result);
                        scr = scannerStack.pop();
                    }
                } else if (!nameCommand.equals("")) {
                    if (pathStack.isEmpty()) {
                        System.out.println("Команды с именем " + nameCommand + " не существует. Проверьте правильность написания и попробуйте ещё раз.");
                    } else {
                        throw new ScriptException("Ошибка в скрипте: команды с именем " + nameCommand + " не существует.");
                    }
                }
            } catch (ScriptException e) {
                System.out.println(e.getMessage());
                System.out.println("Введите +, если хотите продолжить работу со скриптом, пропустив эту команду, и -, если хотите завершить выполнение скрипта");
                boolean answer = ArgParser.parseYesNot();
                if (answer) {
                    StringJoiner findCommand = new StringJoiner("|");
                    for (String name : commands.keySet()) {
                        findCommand.add(name);
                    }
                    while (!scr.hasNext(findCommand.toString()) & scr.hasNextLine()) {
                        scr.nextLine();
                    }
                } else {
                    scr.close();
                    scr = scannerStack.pop();
                    Path path = pathStack.pop();
                    System.out.println("Завершена работа файла " + path.getFileName());
                }
            } catch (OutOfMemoryError e) {
                System.out.println("Программа дошла до переполнения кучи");
            }
            while (!(pathStack.isEmpty() || scr.hasNextLine())) {
                scr.close();
                scr = scannerStack.pop();
                Path path = pathStack.pop();
                System.out.println("Завершена работа файла " + path.getFileName());
            }
        }
    }
}
