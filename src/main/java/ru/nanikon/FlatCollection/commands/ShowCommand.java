package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * output all elements of the collection in a string representation to the standard output stream
 */

public class ShowCommand implements Command{
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {};
    private String information = "'show' - вывести в стандартный поток вывода все элементы коллекции в строковом представлении";

    public ShowCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        System.out.println(this.collection.toLongString());
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
