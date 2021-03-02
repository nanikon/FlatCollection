package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 *
 */
public class IdArg extends AbstractArgument<Integer> {
    private CollectionManager collection;

    public void setCollection(CollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void setValue(String value) {
        try {
            Integer result = Integer.valueOf(value);
            if (result <= 0) {
                throw new NotPositiveNumberException("Аргумент id должен быть больше 0");
            }
            Flat flat = collection.getById(result);
            if (flat == null) {
                throw new NullPointerException("Элемента с таким id не найдено");
            }
            this.value = result;
        } catch (NumberFormatException | NotPositiveNumberException e) {
            if (value.equals("")) {
                throw new NullPointerException("Аргумент id не найден");
            } else {
                throw new NumberFormatException("Аргумент id должен быть числом");
            }
        }
    }
}
