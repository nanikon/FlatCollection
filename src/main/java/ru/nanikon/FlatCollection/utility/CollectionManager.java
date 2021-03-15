package ru.nanikon.FlatCollection.utility;

import ru.nanikon.FlatCollection.data.Flat;
import ru.nanikon.FlatCollection.data.Transport;
import ru.nanikon.FlatCollection.data.View;
import ru.nanikon.FlatCollection.exceptions.FileCollectionException;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Performs all work with the collection. Depends on the parser of the file with the collection
 */

public class CollectionManager {
    private LinkedList<Flat> flatsCollection = new LinkedList<>();
    private JsonLinkedListParser parser;
    private String saveTime;

    public CollectionManager(JsonLinkedListParser parser) throws IOException, FileCollectionException {
        this.parser = parser;
        loadCollection();
        saveTime = parser.getSaveTime().toString().substring(0,10) + " " + parser.getSaveTime().toString().substring(11,19);
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

    public String getType() { return Flat.class.getName(); }

    public String getCreationDate() throws IOException {
        return parser.getCreationDate().toString().substring(0,10) + " " + parser.getCreationDate().toString().substring(11,19);
    }

    public String getFileName() { return parser.getPath(); }

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

    public void loadCollection() throws IOException, FileCollectionException {
        flatsCollection = parser.read();
    }

    public void clearCollection() {
        flatsCollection.clear();
    }

    public void saveCollection() throws IOException {
        parser.write(flatsCollection);
        String now = java.time.ZonedDateTime.now().toString();
        saveTime = now.substring(0, 10) + " " + now.substring(11, 19);
    }

    public String getSaveTime() {
        return saveTime;
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
        }
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
