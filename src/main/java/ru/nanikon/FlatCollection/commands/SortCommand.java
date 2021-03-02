package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

import java.util.Collections;

public class SortCommand implements Command{
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {};
    private String information = "'sort' - отсортировать коллекцию в естественном порядке";

    public SortCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        collection.sortCollection();
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
