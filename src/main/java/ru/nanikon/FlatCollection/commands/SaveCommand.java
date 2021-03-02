package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

import java.io.IOException;

/**
 * save the collection to a file
 */
public class SaveCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {};
    private String information = "'save' - сохранить коллекцию в файл";

    public SaveCommand(CollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(AbstractArgument<?>[] params) {
        try {
            collection.saveCollection();
        } catch (IOException e) {
            System.out.println("Невозможно открыть на запись файл, к которому привязана коллекция. проверьте его и попробуйте ещё раз");
        }
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
