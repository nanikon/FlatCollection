package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.FlatArg;
import ru.nanikon.FlatCollection.arguments.IdArg;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.FlatBuilder;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * добавить новый элемент в заданную позицию
 */
public class InsertCommand implements Command{
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new IdArg(), new FlatArg()};
    private String information = "'insert_at index {element}' - добавить новый элемент в заданную позицию";

    public InsertCommand(CollectionManager collection) {
        this.collection = collection;
        ((IdArg) params[0]).setCollection(collection);
    }
    @Override
    public void execute(AbstractArgument<?>[] params) {
        int id = ((IdArg) params[0]).getValue();
        FlatBuilder builder = ((FlatArg) params[0]).getBuilder();
        builder.setId(collection.generateNextId());
        Flat flat = builder.getResult();
        collection.addById(flat, id);
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
