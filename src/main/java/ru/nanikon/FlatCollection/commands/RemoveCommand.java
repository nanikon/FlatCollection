package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.IdArg;
import ru.nanikon.FlatCollection.utility.CollectionManager;

public class RemoveCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new IdArg()};
    private String information = "'remove_by_id id' - удалить элемент из коллекции по его id";

    public RemoveCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        int id = ((IdArg) params[0]).getValue();
        collection.removeById(id);
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
