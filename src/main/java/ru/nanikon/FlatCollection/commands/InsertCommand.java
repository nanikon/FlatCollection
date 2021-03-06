package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.FlatArg;
import ru.nanikon.FlatCollection.arguments.IndexArg;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.FlatBuilder;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * добавить новый элемент в заданную позицию
 */
public class InsertCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new IndexArg(), new FlatArg()};
    private String information = "'insert_at index {element}' - добавить новый элемент в заданную позицию";

    public InsertCommand(CollectionManager collection) {
        this.collection = collection;
        ((IndexArg) params[0]).setCollection(collection);
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public String execute(AbstractArgument<?>[] params) {
        int id = ((IndexArg) params[0]).getValue();
        FlatBuilder builder = ((FlatArg) params[1]).getBuilder();
        try {
            builder.setId(String.valueOf(collection.generateNextId()));
        } catch (NotPositiveNumberException e) {
            System.out.println("Этой ошибки быть не должно, так как id генерируется автоматически");
        }
        Flat flat = builder.getResult();
        collection.addById(flat, id - 1);
        return "В коллекцию успешно добавлен элемент {" + flat.toLongString() + "}";
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
