package ru.nanikon.FlatCollection.arguments;

import java.io.IOException;

/**
 * Superclass of all arguments. It and its heirs are required to validate user-entered arguments before passing them to commands.
 * @param <T>
 */
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
