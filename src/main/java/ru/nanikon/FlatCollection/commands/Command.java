package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

/**
 * Universal interface for all teams
 */
public interface Command {
    /**
     * running the command
     * @param params - params of Command
     */
    void execute(AbstractArgument<?>[] params);

    /**
     *
     * @return - returns the help for the command. For help command
     */
    String getInformation();

    /**
     *
     * @return - returns the list of arguments required for the command to work, which must be obtained from the user
     */
    AbstractArgument<?>[] getArgs();
}
