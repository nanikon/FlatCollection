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

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public String execute(AbstractArgument<?>[] params) {
        return "Среднее значение поля количество комнат по всем квартирам коллекции: " + collection.getAverageNumberOfRooms();
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
