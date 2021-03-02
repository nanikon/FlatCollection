package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.data.Coordinates;
import ru.nanikon.FlatCollection.data.CoordinatesBuilder;

/**
 * Corresponds to the Coordinates object. Checks the x and y fields via builder and returns the finished object
 */
public class CoordinatesArg extends ObjectArgument<Coordinates> {
    private CoordinatesBuilder builder;
    {
        builder = new CoordinatesBuilder();
        params.put(new String[]{" 2", "координата x", "Это должно быть целое или вещественное число с точкой в виде десятичной дроби"}, builder::setX);
        params.put(new String[]{" 3", "координата y", "Это должно быть целое или вещественное число с точкой в виде десятичной дроби"}, builder::setY);
    }

    public void setValue(String value) {
         this.value = builder.getResult();
    }

    public CoordinatesBuilder getBuilder() {
        return builder;
    }

    public void clear() {
        builder.reset();
        value = null;
    }
}
