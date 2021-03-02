package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.TransportArg;
import ru.nanikon.FlatCollection.data.Transport;
import ru.nanikon.FlatCollection.utility.CollectionManager;

public class RemoveAnyByTransportCommand implements Command{
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new TransportArg()};
    private String information = "'remove_any_by_transport transport' - удалить из коллекции один элемент, значение поля transport которого эквивалентно заданному";

    public RemoveAnyByTransportCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(AbstractArgument<?>[] params) {
        Transport transport = ((TransportArg) params[0]).getValue();
        collection.removeByTransport(transport);
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
