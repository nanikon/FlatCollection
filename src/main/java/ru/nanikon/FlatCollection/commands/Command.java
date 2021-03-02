package ru.nanikon.FlatCollection.commands;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;

public interface Command {
    void execute(AbstractArgument<?>[] params);
    String getInformation();
    AbstractArgument<?>[] getArgs();
}
