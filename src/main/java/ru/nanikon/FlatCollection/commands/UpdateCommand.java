package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.FlatArg;
import ru.nanikon.FlatCollection.arguments.IdArg;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.FlatBuilder;
import ru.nanikon.FlatCollection.exceptions.BooleanInputException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;
import ru.nanikon.FlatCollection.utility.CollectionManager;

import java.lang.reflect.Field;
import java.util.HashSet;

public class UpdateCommand implements Command {
    private CollectionManager collection;
    private AbstractArgument<?>[] params = {new IdArg(), new FlatArg()};
    private String information = "'update id {element}' - обновить значение элемента коллекции, id которого равен заданному";

    public UpdateCommand(CollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(AbstractArgument<?>[] params) {
        try {
            int id = ((IdArg) params[0]).getValue();
            Flat oldFlat = collection.getById(id);
            FlatBuilder oldBuilder = ((FlatArg) params[1]).getBuilder();
            FlatBuilder newBuilder = new FlatBuilder();
            HashSet<String> newFields = oldBuilder.getChange();
            newBuilder.setId(oldFlat.getId());
            newBuilder.setCreationDate(oldFlat.getCreationDate());
            newBuilder.setName(newFields.contains("name") ? oldBuilder.getName() : oldFlat.getName());
            newBuilder.setX(newFields.contains("x") ? String.valueOf(oldBuilder.getX()) : String.valueOf(oldFlat.getX()));
            newBuilder.setY(newFields.contains("y") ? String.valueOf(oldBuilder.getY()) : String.valueOf(oldFlat.getY()));
            newBuilder.setArea(newFields.contains("area") ? String.valueOf(oldBuilder.getArea()) : String.valueOf(oldFlat.getArea()));
            newBuilder.setNumberOfRooms(newFields.contains("numberOfRooms") ? String.valueOf(oldBuilder.getNumberOfRooms()) : String.valueOf(oldFlat.getNumberOfRooms()));
            String builderCentralHeating = oldBuilder.isCentralHeating() ? "+" : "-";
            String flatCentralHeating = oldFlat.isCentralHeating() ? "+" : "-";
            newBuilder.setCentralHeating(newFields.contains("centralHeating") ? builderCentralHeating : flatCentralHeating);
            newBuilder.setView(newFields.contains("view") ? oldBuilder.getView().name() : oldFlat.getView().name());
            newBuilder.setTransport(newFields.contains("transport") ? oldBuilder.getTransport().name() : oldFlat.getTransport().name());
            newBuilder.setHouseName(newFields.contains("nameHouse") ? oldBuilder.getHouseName() : oldFlat.getHouseName());
            newBuilder.setYear(newFields.contains("year") ? String.valueOf(oldBuilder.getYear()) : String.valueOf(oldFlat.getYear()));
            newBuilder.setNumberOfFloors(newFields.contains("numberOfFloors") ? String.valueOf(oldBuilder.getNumberOfFloors()) : String.valueOf(oldFlat.getNumberOfFloors()));
            Flat newFlat = newBuilder.getResult();
            collection.setById(newFlat, id - 1);
        } catch (NotPositiveNumberException| BooleanInputException e) {
            System.out.println("Меня не должно существовать но кря)");
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
