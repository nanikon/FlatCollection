package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.data.View;

public class ViewArg extends EnumArgument<View> {
    @Override
    public void setValue(String value) {
        try {
            this.value = View.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Поле вид не может принять значение " + value, e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Поле вид не может быть null");
        }
    }

    @Override
    public String getConstants() {
        StringBuilder result = new StringBuilder();
        for (View view : View.values()) {
            result.append(view.name()).append(" ");
        }
        return result.toString();
    }
}
