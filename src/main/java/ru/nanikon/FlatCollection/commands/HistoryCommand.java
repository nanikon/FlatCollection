package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

import java.util.Deque;

/**
 * output the last 8 commands (without their arguments)
 */
public class HistoryCommand implements Command{
    Deque<String> history;
    private AbstractArgument<?>[] params = {};
    private String information = "'history' - вывести последние 8 команд (без их аргументов)";

    public HistoryCommand(Deque<String> history) {
        this.history = history;
    }
    @Override
    public void execute(AbstractArgument<?>[] params) {
        for (String command: history) {
            if (!(command == null)) {
                System.out.println(command);
            }
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
