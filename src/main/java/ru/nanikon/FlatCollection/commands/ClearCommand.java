package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

public class ClearCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {};
    private String information = "'clear' - очистить коллекцию";

    public ClearCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        collection.clearCollection();
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
