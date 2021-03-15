package ru.nanikon.FlatCollection.utility;

import com.google.gson.*;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.exceptions.FileCollectionException;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class ListJsonConverter implements JsonDeserializer<LinkedList<Flat>> {
    @Override
    public LinkedList<Flat> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LinkedList<Flat> result = new LinkedList<>();
        if (!jsonElement.isJsonArray()) {
            throw new JsonParseException("В файле не обнаружена коллекция");
        }
        JsonArray flats = jsonElement.getAsJsonArray();
        int i = 1;
        for (JsonElement flat : flats) {
            try {
                result.add(jsonDeserializationContext.deserialize(flat, Flat.class));
                i++;
            } catch (JsonParseException e) {
                throw new JsonParseException(e.getMessage() + " в Объекте №" + i);
            }
        }
        return result;
    }
}
