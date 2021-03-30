package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

import java.util.HashMap;

/**
 * display help for available commands
 */
public class HelpCommand implements ru.nanikon.FlatCollection.commands.Command {
    private HashMap<String, ru.nanikon.FlatCollection.commands.Command> commands;
    private AbstractArgument<?>[] params = {};
    private String information = "'help' - вывести справку по доступным командам";

    public HelpCommand(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public String execute(AbstractArgument<?>[] params) {
        StringBuilder result = new StringBuilder();
        result.append("Справка по командам:").append("\n");
        for (String nameCommand : commands.keySet()) {
            result.append(commands.get(nameCommand).getInformation()).append("\n");
        }
        return result.toString().trim();
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
