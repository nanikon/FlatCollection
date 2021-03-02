package ru.nanikon.FlatCollection.arguments;

import java.io.IOException;

abstract public class AbstractArgument<T> {
    protected T value;

    public T getValue() {
        return value;
    }

    abstract public void setValue(String value) throws IOException;

    public boolean isObject() {
        return false;
    }

    public boolean isEnum() {
        return false;
    }
}
