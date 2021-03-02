package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.exceptions.BooleanInputException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowConsumer<T> {
    void accept (T value) throws NotPositiveNumberException, BooleanInputException;
}
