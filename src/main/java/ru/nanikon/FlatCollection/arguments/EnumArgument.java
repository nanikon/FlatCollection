package ru.nanikon.FlatCollection.arguments;

abstract public class EnumArgument<T extends Enum<?>> extends AbstractArgument<T> {
    public boolean isObject() {
        return false;
    }

    public boolean isEnum() {
        return true;
    }

    abstract public void setValue(String value);
    abstract public String getConstants();
}
