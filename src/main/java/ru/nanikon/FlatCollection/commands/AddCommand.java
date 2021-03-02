package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.FlatArg;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.FlatBuilder;
import ru.nanikon.FlatCollection.utility.CollectionManager;

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
        builder.setId(collection.generateNextId());
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
