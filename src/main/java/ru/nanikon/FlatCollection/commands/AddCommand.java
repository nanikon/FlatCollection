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

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public String execute(AbstractArgument<?>[] params) {
        FlatBuilder builder = ((FlatArg) params[0]).getBuilder();
        try {
            builder.setId(String.valueOf(collection.generateNextId()));
        } catch (NotPositiveNumberException e) {
            System.out.println("Этой ошибки быть не должно, id генерится автоматически");
        }
        Flat flat = builder.getResult();
        collection.addLast(flat);
        return "Элемент {" + flat.toLongString() + "} успешно добавлен в коллекцию";
    }

    /**
     * @return - returns the list of arguments required for the command to work, which must be obtained from the user
     */
    @Override
    public AbstractArgument<?>[] getArgs() {
        return params;
    }

    @Override
    public String getInformation() {
        return information;
    }
}
