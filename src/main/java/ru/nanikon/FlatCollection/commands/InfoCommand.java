package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

import java.io.IOException;

public class InfoCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {};
    private String information = "'info' - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)";

    public InfoCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        System.out.println("Информация о коллекции: ");
        System.out.println("тип хранимых объектов: " + collection.getType());
        System.out.println("количество элементов: " + collection.getSize());
        try {
            System.out.println("дата инициализации: " + collection.getCreationDate());
        } catch (IOException e) {
            System.out.println("Файла, связанного с коллекцией не существует. Проверьте его наличие и попробуйте ещё раз.");
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
