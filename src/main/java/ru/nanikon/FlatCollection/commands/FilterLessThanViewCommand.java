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

    @Override
    public void execute(AbstractArgument<?>[] params) {
        View view = ((ViewArg) params[0]).getValue();
        System.out.println("Объекты коллекции со значением поля вид меньше, чем " + view);
        System.out.println(collection.viewFilteredInfo(view));
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
