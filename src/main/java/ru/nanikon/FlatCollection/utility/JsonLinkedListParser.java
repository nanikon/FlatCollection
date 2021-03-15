package ru.nanikon.FlatCollection.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import ru.nanikon.FlatCollection.data.Flat;
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

    public String getPath() {return path;}

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
        Gson gson = new GsonBuilder().registerTypeAdapter(Flat.class, new FlatJsonConverter()).registerTypeAdapter(type, new ListJsonConverter()).create();
        try {
            LinkedList<Flat> result = gson.fromJson(String.valueOf(json), type);
            return result;
        } catch (JsonSyntaxException e) {
            throw new FileCollectionException("В файле не найдены объекты коллекции и/или объекты, являющиеся их частью, или же сам файл некорректен");
        } catch (Exception e) {
            throw new FileCollectionException("В файле содержится ошибка: " + e.getMessage());
        }
    }

    public FileTime getCreationDate() throws IOException {
        return Files.readAttributes(new File(path).toPath(), BasicFileAttributes.class).creationTime();
    }

    public FileTime getSaveTime() throws IOException {
        return Files.readAttributes(new File(path).toPath(), BasicFileAttributes.class).lastModifiedTime();
    }
}
