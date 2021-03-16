package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * sort the collection in the natural order
 */
public class SortCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {};
    private String information = "'sort' - отсортировать коллекцию в естественном порядке";

    public SortCommand(CollectionManager collection) {
        this.collection = collection;
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public void execute(AbstractArgument<?>[] params) {
        collection.sortCollection();
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
