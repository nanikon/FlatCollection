package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.IdArg;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * delete an item from the collection by its id
 */
public class RemoveCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new IdArg()};
    private String information = "'remove_by_id id' - удалить элемент из коллекции по его id";

    public RemoveCommand(CollectionManager collection) {
        this.collection = collection;
        ((IdArg) params[0]).setCollection(collection);
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public void execute(AbstractArgument<?>[] params) {
        int id = ((IdArg) params[0]).getValue();
        collection.removeById(id);
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
