package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.FlatArg;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.FlatBuilder;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * Adds an object to the collection
 */
public class AddCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new FlatArg()};
    private String information = "'add {element}' - добавить новый элемент в коллекцию";

    public AddCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        FlatBuilder builder = ((FlatArg) params[0]).getBuilder();
        try {
            builder.setId(String.valueOf(collection.generateNextId()));
        } catch (NotPositiveNumberException e) {
            e.printStackTrace();
        }
        Flat flat = builder.getResult();
        collection.addLast(flat);
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
