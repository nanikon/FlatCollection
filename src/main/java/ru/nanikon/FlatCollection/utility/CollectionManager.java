package ru.nanikon.FlatCollection.utility;

import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.Transport;
import ru.nanikon.FlatCollection.data.View;

import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.Collections;
import java.util.LinkedList;

public class CollectionManager {
    private LinkedList<Flat> flatsCollection = new LinkedList<>();
    private JsonLinkedListParser parser;

    public CollectionManager(JsonLinkedListParser parser) throws IOException {
        this.parser = parser;
        loadCollection();
    }

    public int generateNextId() {
        if (flatsCollection.isEmpty()) {
            return 1;
        } else {
            return flatsCollection.getLast().getId() + 1;
        }
    }

    public int getSize() {
        return flatsCollection.size();
    }

    public String getType() { return Flat.class.toString(); }

    public FileTime getCreationDate() throws IOException {
        return parser.getCreationDate();
    }

    public Flat getFirst() {
        return flatsCollection.getLast();
    }

    public Flat getLast() {
        return flatsCollection.getFirst();
    }

    public Flat getById(int id) {
        for (Flat flat : flatsCollection) {
            if (flat.getId() == id) {
                return flat;
            }
        }
        return null;
    }

    public void addLast(Flat flat) {
        flatsCollection.add(flat);
    }

    public void addById(Flat flat, int id) {
        flatsCollection.add(id, flat);  //TODO: Подумать, как сделать быстрее
    }

    public void setById(Flat flat, int id) {
        flatsCollection.set(id, flat);
    }

    public void removeById(int id) {
        flatsCollection.remove(id);
    }

    public void removeByTransport(Transport transport) {
        int id = -1;
        for (Flat flat : flatsCollection) {
            if (flat.getTransport().equals(transport)) {
                id = flat.getId();
                break;
            }
        }
        if (id == -1) {
            System.out.println("Не найдено квартир, у которых значение поля транспорт равно " + transport);
        } else {
            flatsCollection.remove(id);
        }
    }

    public void loadCollection() throws IOException {
        flatsCollection = parser.read();
    }

    public void clearCollection() {
        flatsCollection.clear();
    }

    public void saveCollection() throws IOException {
        parser.write(flatsCollection);
    }

    public void sortCollection() {
        Collections.sort(flatsCollection);
    }

    public String toLongString() {
        StringBuilder info = new StringBuilder();
        for (Flat flat: flatsCollection) {
            info.append(flat.toLongString()).append("\n");
        }
        return info.toString().trim();
    }

    public int getAverageNumberOfRooms() {
        long result = 0;
        for (Flat flat : flatsCollection) {
            result += flat.getNumberOfRooms();
        };
        result = result / getSize();
        return (int) result;
    }

    public String viewFilteredInfo(View view) {
        StringBuilder result = new StringBuilder();
        for (Flat flat : flatsCollection) {
            if (flat.getView().compareTo(view) < 0) {
                result.append(flat.toLongString()).append("\n");
            }
        }
        return result.toString().trim();
    }
}
