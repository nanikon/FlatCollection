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
    public void execute(AbstractArgument<?>[] params) {
        System.out.println("Справка по командам:");
        for (String nameCommand : commands.keySet()) {
            System.out.println(commands.get(nameCommand).getInformation());
        }
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
