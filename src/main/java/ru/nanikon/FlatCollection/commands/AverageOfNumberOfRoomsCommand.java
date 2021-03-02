package ru.nanikon.FlatCollection.commands;


import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * Calculates the average value of the numberOfRooms field for the entire collection
 */
public class AverageOfNumberOfRoomsCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {};
    private String information = "'average_of_number_of_rooms' - вывести среднее значение поля numberOfRooms для всех элементов коллекции";

    public AverageOfNumberOfRoomsCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        System.out.println("Среднее значение поля количество комнат по всем квартирам коллекции: " + collection.getAverageNumberOfRooms());
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
