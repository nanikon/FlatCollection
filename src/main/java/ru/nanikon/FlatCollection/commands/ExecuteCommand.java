package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.FileArg;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Stack;

/**
 * Executes a script in which the commands are written in the same form as they are entered in interactive mode
 */
public class ExecuteCommand implements Command{
    Stack<Path> pathStack;
    Stack<Scanner> scannerStack;
    private AbstractArgument<?>[] params = {new FileArg()};
    private String information = "'execute_script file_name' - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";

    public ExecuteCommand(Stack<Scanner> scannerStack, Stack<Path> pathStack) {
        this.scannerStack = scannerStack;
        this.pathStack = pathStack;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        File file = new File(((FileArg) params[0]).getValue());
        pathStack.push(file.toPath());
        try {
            scannerStack.push(new Scanner(file));
            //System.out.println(scannerStack.size());
        } catch (FileNotFoundException e) {
            System.out.println("Этой ошибки быть не должно");
        }
    }

    @Override
    public String getInformation() {
        return information;
    }

    @Override
    public AbstractArgument<?>[] getArgs() {
        return params;
    }
}
