package ru.nanikon.FlatCollection.arguments;

import ru.nanikon.FlatCollection.data.Transport;

public class TransportArg extends EnumArgument<Transport> {
    @Override
    public void setValue(String value) {
        try {
            this.value = Transport.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Поле транспорт не может принять значение " + value, e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Поле транспорт не может быть null");
        }
    }

    @Override
    public String getConstants() {
        StringBuilder result = new StringBuilder();
        for (Transport transport : Transport.values()) {
            result.append(transport.name()).append(" ");
        }
        return result.toString();
    }
}
