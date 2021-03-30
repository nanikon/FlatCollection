package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.ViewArg;
import ru.nanikon.FlatCollection.data.View;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * output elements whose view field value is less than the specified value
 */
public class FilterLessThanViewCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new ViewArg()};
    private String information = "'filter_less_than_view view' - вывести элементы, значение поля view которых меньше заданного";

    public FilterLessThanViewCommand(CollectionManager collection) {
        this.collection = collection;
    }

    /**
     * running the command
     * @param params - params of Command
     */
    @Override
    public String execute(AbstractArgument<?>[] params) {
        View view = ((ViewArg) params[0]).getValue();
        String result = collection.viewFilteredInfo(view);
        if (result.equals("")) {
            return "Объектов коллекции со значением поля вид меньше, чем " + view + " не найдено";
        }
        return "Объекты коллекции со значением поля вид меньше, чем " + view + "\n" + collection.viewFilteredInfo(view);
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
