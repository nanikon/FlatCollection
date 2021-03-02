package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

import java.util.HashMap;

public class HelpCommand implements Command{
    private HashMap<String, Command> commands;
    private AbstractArgument<?>[] params = {};
    private String information = "'help' - вывести справку по доступным командам";

    public HelpCommand(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        System.out.println("Справка по командам:");
        for (String nameCommand : commands.keySet()) {
            System.out.println(commands.get(nameCommand).getInformation());
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
