package ru.nanikon.FlatCollection.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.FlatBuilder;
import ru.nanikon.FlatCollection.exceptions.FileCollectionException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Parses the LinkedList collection to a json file and back
 */

public class JsonLinkedListParser {
    private final String path;
    private final Type type;

    public JsonLinkedListParser(String path) {
        this.path = path;
        type = new TypeToken<LinkedList<Flat>>(){}.getType();
    }

    public void write(LinkedList<Flat> data) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(json);
        } catch (IOException e) {
            throw new IOException("Невозможно получить доступ к файлу", e);
        }
    }

    public LinkedList<Flat> read() throws IOException, FileCollectionException {
        StringBuilder json = new StringBuilder();
        try (Scanner scr = new Scanner(new File(path))) {
            while (scr.hasNextLine()) {
                json.append(scr.nextLine());
            }
        } catch (IOException e) {
            throw new IOException("Не удается найти или прочитать файл", e);
        }
        Gson gson = new Gson();
        LinkedList<Flat> template = gson.fromJson(json.toString(), type);
        LinkedList<Flat> result = new LinkedList<Flat>();
        FlatBuilder builder = new FlatBuilder();
        int i = 1;
        for (Flat flat : template) {
            try {
                builder.reset();
                builder.setId(flat.getId());
                builder.setName(flat.getName());
                builder.setX(String.valueOf(flat.getX()));
                builder.setY(String.valueOf(flat.getY()));
                builder.setArea(String.valueOf(flat.getArea()));
                builder.setNumberOfRooms(String.valueOf(flat.getNumberOfRooms()));
                String heating = flat.isCentralHeating() ? "+" : "-";
                builder.setCentralHeating(heating);
                builder.setView(flat.getView().name());
                builder.setTransport(flat.getTransport().name());
                builder.setHouseName(flat.getHouseName());
                builder.setYear(String.valueOf(flat.getYear()));
                builder.setNumberOfFloors(String.valueOf(flat.getNumberOfFloors()));
                result.add(builder.getResult());
            } catch (Exception e) {
                throw new FileCollectionException("В файле содержится ошибка в объекте №" + i + ": " + e.getMessage());
            }
            i++;
        }
        return result;
    }

    public FileTime getCreationDate() throws IOException {
        return Files.readAttributes(new File(path).toPath(), BasicFileAttributes.class).creationTime();
    }
}
