package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

import java.nio.file.Path;
import java.util.Scanner;
import java.util.Stack;

/**
 * Terminates the execution of a script or all programs. Does not save the collection to a file!
 */
public class ExitCommand implements Command {
    private Stack<Path> pathStack;
    Stack<Scanner> scannerStack;
    private AbstractArgument<?>[] params = {};
    private String information = "'exit' - завершить программу (без сохранения в файл))";

    public ExitCommand(Stack<Scanner> scannerStack, Stack<Path> pathStack) {
        this.scannerStack = scannerStack;
        this.pathStack = pathStack;
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public void execute(AbstractArgument<?>[] params) {
        if (pathStack.isEmpty()) {
            System.exit(0);
        } else {
            Path path = pathStack.pop();
            Scanner scr = scannerStack.pop();
            scr.close();
        }
    }

    /**
     * @return - returns the list of arguments required for the command to work, which must be obtained from the user
     */
    @Override
    public AbstractArgument<?>[] getArgs() {
        return params;
    }

    /**
     * @return - returns the help for the command. For help command
     */
    @Override
    public String getInformation() {
        return information;
    }
}
