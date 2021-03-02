package ru.nanikon.FlatCollection.arguments;

public class IdArg extends AbstractArgument<Integer> {
    @Override
    public void setValue(String value) {
        try {
            this.value = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            if (value.equals("")) {
                throw new NullPointerException("Аргумент id не найден");
            } else {
                throw new NumberFormatException("Аргумент id должен быть числом");
            }
        }
    }
}
