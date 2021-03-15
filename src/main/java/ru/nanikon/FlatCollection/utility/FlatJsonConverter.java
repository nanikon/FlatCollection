package ru.nanikon.FlatCollection.utility;

import com.google.gson.*;
import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.FlatBuilder;
import ru.nanikon.FlatCollection.exceptions.BooleanInputException;
import ru.nanikon.FlatCollection.exceptions.FileCollectionException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlatJsonConverter implements JsonDeserializer<Flat> {
    @Override
    public Flat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        FlatBuilder builder = new FlatBuilder();
        builder.reset();
        Flat result = null;
        try {
            builder.setId(jsonObject.get("id").getAsString());
            builder.setName(jsonObject.get("name").getAsString());

            JsonObject coords = jsonObject.get("coordinates").getAsJsonObject();
            builder.setX(coords.get("x").getAsString());
            builder.setY(coords.get("y").getAsString());

            JsonObject creationDate = jsonObject.get("creationDate").getAsJsonObject();
            JsonObject date = creationDate.get("dateTime").getAsJsonObject().get("date").getAsJsonObject();
            JsonObject time = creationDate.get("dateTime").getAsJsonObject().get("time").getAsJsonObject();
            LocalDateTime ldt = LocalDateTime.of(date.get("year").getAsInt(), date.get("month").getAsInt(), date.get("day").getAsInt(), time.get("hour").getAsInt(),  time.get("minute").getAsInt(),  time.get("second").getAsInt(),  time.get("nano").getAsInt());
            ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of(creationDate.get("zone").getAsJsonObject().get("id").getAsString()));
            builder.setCreationDate(zdt);

            builder.setArea(jsonObject.get("area").getAsString());
            builder.setNumberOfRooms(jsonObject.get("numberOfRooms").getAsString());
            builder.setCentralHeating(jsonObject.get("centralHeating").getAsBoolean() ? "+" : "-");
            builder.setView(jsonObject.get("view").getAsString());
            builder.setTransport(jsonObject.get("transport").getAsString());

            JsonObject house = jsonObject.get("house").getAsJsonObject();
            builder.setHouseName(house.get("name").getAsString());
            builder.setYear(house.get("year").getAsString());
            builder.setNumberOfFloors(house.get("numberOfFloors").getAsString());
            result = builder.getResult();
        } catch (NumberFormatException e) {
            throw new JsonParseException(e.getMessage());
        } catch (NullPointerException e) {
            throw new JsonParseException("Не найдены все необходимые поля");
        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
        return result;
    }
}
