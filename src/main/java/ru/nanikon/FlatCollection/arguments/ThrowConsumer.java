package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.exceptions.BooleanInputException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;

/**
 * Like standart Consumer<T> but its accept method can throw exceptions
 * @param <T>
 */
@FunctionalInterface
public interface ThrowConsumer<T> {
    void accept (T value) throws NotPositiveNumberException, BooleanInputException;
}
