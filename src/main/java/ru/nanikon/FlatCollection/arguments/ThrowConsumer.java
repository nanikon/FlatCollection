package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.exceptions.BooleanInputException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;

/**
 * Like standart Consumer&lt;T&gt; but its accept method can throw exceptions
 * @param <T> type of argument value
 */
@FunctionalInterface
public interface ThrowConsumer<T> {
    /**
     * takes a value and returns nothing
     * @param value accepted value
     * @throws NotPositiveNumberException
     * @throws BooleanInputException
     */
    void accept (T value) throws NotPositiveNumberException, BooleanInputException;
}
