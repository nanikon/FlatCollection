package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

import java.nio.file.Path;
import java.util.Scanner;
import java.util.Stack;

public class ExitCommand implements Command {
    private Stack<Path> pathStack;
    Stack<Scanner> scannerStack;
    private AbstractArgument<?>[] params = {};
    private String information = "'exit' - завершить программу (без сохранения в файл))";

    public ExitCommand(Stack<Scanner> scannerStack, Stack<Path> pathStack) {
        this.scannerStack = scannerStack;
        this.pathStack = pathStack;
    }

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

    @Override
    public AbstractArgument<?>[] getArgs() {
        return params;
    }

    @Override
    public String getInformation() {
        return information;
    }
}
