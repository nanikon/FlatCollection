package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.FileArg;
import ru.nanikon.FlatCollection.utility.ArgParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Stack;

/**
 * Executes a script in which the commands are written in the same form as they are entered in interactive mode
 */
public class ExecuteCommand implements Command {
    Stack<Path> pathStack;
    Stack<Scanner> scannerStack;
    private final AbstractArgument<?>[] params = {new FileArg()};
    private String information = "'execute_script file_name' - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";

    public ExecuteCommand(Stack<Scanner> scannerStack, Stack<Path> pathStack) {
        this.scannerStack = scannerStack;
        this.pathStack = pathStack;
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public String execute(AbstractArgument<?>[] params) {
        String fileName = ((FileArg) params[0]).getValue();
        File file = new File(fileName);
        Enumeration<Path> enu = pathStack.elements();
        int entry = 0;
        while (enu.hasMoreElements()) {
            if (enu.nextElement().equals(file.toPath())) {
                entry++;
                if (entry == 2) {
                    break;
                }
            }
        }
        if (entry == 1) {
            System.out.println("Обнаружен рекурсивный вызов файла " + fileName + ". Введите +, если желаете продолжить рекурсию, но тогда приложение может упасть. Введите -, если хотите пропустить эту команду и продолжить дальше выполнение скрипта.");
            boolean answer = ArgParser.parseYesNot();
            if (!answer) {
                return "Команда вызова скрипта пропущена, скрипт выполняется дальше";
            }
        }
        pathStack.push(file.toPath());
        try {
            scannerStack.push(new Scanner(file, "UTF-8"));
        } catch (FileNotFoundException e) {
            return "Исполняемый файл не найден";
        }
        return "начинается выполнение файла " + fileName;
    }

    /**
     * @return - returns the help for the command. For help command
     */
    @Override
    public String getInformation() {
        return information;
    }

    /**
     * @return - returns the list of arguments required for the command to work, which must be obtained from the user
     */
    @Override
    public AbstractArgument<?>[] getArgs() {
        return params;
    }
}
