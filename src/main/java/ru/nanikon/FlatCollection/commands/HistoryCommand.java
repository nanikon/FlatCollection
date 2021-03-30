package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

import java.util.Deque;

/**
 * output the last 8 commands (without their arguments)
 */
public class HistoryCommand implements Command {
    Deque<String> history;
    private AbstractArgument<?>[] params = {};
    private String information = "'history' - вывести последние 8 команд (без их аргументов)";

    public HistoryCommand(Deque<String> history) {
        this.history = history;
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public String execute(AbstractArgument<?>[] params) {
        StringBuilder result = new StringBuilder();
        for (String command: history) {
            if (!(command == null)) {
                result.append(command).append("\n");
            }
        }
        return result.toString().trim();
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
