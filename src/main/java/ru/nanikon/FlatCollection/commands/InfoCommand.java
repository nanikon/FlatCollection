package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

import java.io.IOException;

/**
 * output information about the collection (type, initialization date, number of elements) to the standard output stream.)
 */
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
        System.out.println("файл, связанный с коллекцией: " + collection.getFileName());
        try {
            System.out.println("дата и время инициализации: " + collection.getCreationDate());
        } catch (IOException e) {
            System.out.println("Файла, связанного с коллекцией не существует. Проверьте его наличие и попробуйте ещё раз.");
        }
        System.out.println("дата и время последнего сохранения в файл: " + collection.getSaveTime());
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
