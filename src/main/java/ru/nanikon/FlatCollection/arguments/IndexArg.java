package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;
import ru.nanikon.FlatCollection.utility.CollectionManager;

/**
 * Checks that the entered position exists in the collection
 */
public class IndexArg extends AbstractArgument<Integer>{
    private CollectionManager collection;

    /**
     * @param collection Collection with which the program works
     */
    public void setCollection(CollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Checks the entered argument and arranges additional work if necessary
     *
     * @param value a file read or from the console a line with an argument value
     */
    @Override
    public void setValue(String value) {
        try {
            int result = Integer.parseInt(value);
            if (result <= 0) {
                throw new NotPositiveNumberException("Аргумент индекс должен быть больше 0");
            }
            int size = collection.getSize();
            if (result > size + 1) {
                throw new NullPointerException("Невозможно вставить элемент на позицию " + result + " так как размер коллекции всего " + size + " и такой позиции пока не существует");
            }
            this.value = result;
        } catch (NumberFormatException e) {
            if (value.equals("")) {
                 throw new NullPointerException("Аргумент индекс не найден");
            } else {
                throw new NumberFormatException("Аргумент индекс должен быть числом");
            }
        }
    }
}
