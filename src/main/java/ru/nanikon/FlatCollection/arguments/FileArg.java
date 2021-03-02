package ru.nanikon.FlatCollection.arguments;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileArg extends AbstractArgument<String> {
    @Override
    public void setValue(String value) throws IOException {
        File file = new File(value);
        try (Scanner scr = new Scanner(file)) {
            this.value = value;
        } catch (IOException e) {
            throw new IOException("Файл с таким именем не удается найти или прочитать. Проверьте его и попробуйте ещё раз");
        }
    }
}
