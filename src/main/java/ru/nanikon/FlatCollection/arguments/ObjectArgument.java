package ru.nanikon.FlatCollection.arguments;

import java.util.HashMap;

/**
 * Superclass of all object arguments.
 * @param <T>
 */
abstract public class ObjectArgument<T> extends AbstractArgument<T> {
    protected HashMap<String[], ThrowConsumer<String>> params = new HashMap<>();

    abstract public void setValue(String value);

    abstract public void clear();

    public HashMap<String[], ThrowConsumer<String>> getParams() {
        return params;
    }

    public boolean isObject() {
        return true;
    }

    public boolean isEnum() {
        return false;
    }
}
