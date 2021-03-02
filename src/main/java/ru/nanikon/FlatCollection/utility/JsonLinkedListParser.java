package ru.nanikon.FlatCollection.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.nanikon.FlatCollection.data.Flat;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.LinkedList;
import java.util.Scanner;

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

    public LinkedList<Flat> read() throws IOException {
        StringBuilder json = new StringBuilder();
        try (Scanner scr = new Scanner(new File(path))) {
            while (scr.hasNextLine()) {
                json.append(scr.nextLine());
            }
        } catch (IOException e) {
            throw new IOException("Не удается найти или прочитать файл", e);
        }
        Gson gson = new Gson();
        return gson.fromJson(json.toString(), type);
    }

    public FileTime getCreationDate() throws IOException {
        return Files.readAttributes(new File(path).toPath(), BasicFileAttributes.class).creationTime();
    }
}
