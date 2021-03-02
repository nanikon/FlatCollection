package ru.nanikon.FlatCollection.data;

import ru.nanikon.FlatCollection.exceptions.BooleanInputException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;

import java.time.ZonedDateTime;
import java.util.HashSet;

/**
 * Builder Flat class. Checks the fields and creates an object. Contains the Coordinates and House builders
 */

public class FlatBuilder {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long area; //Значение поля должно быть больше 0
    private Integer numberOfRooms; //Поле может быть null, Значение поля должно быть больше 0
    private boolean centralHeating;
    private View view; //Поле не может быть null
    private Transport transport; //Поле не может быть null
    private House house; //Поле не может быть null
    private CoordinatesBuilder coordsBuilder = new CoordinatesBuilder();
    private HouseBuilder houseBuilder = new HouseBuilder();
    private HashSet<String> changed = new HashSet<>();

    public void setId(int id) {
        this.id = id;
        changed.add("id");
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new NullPointerException("Поле имя квартиры не может быть пустым!");
        } else {
            this.name = name;
            changed.add("name");
        }
    }

    public void setX(String value) {
        coordsBuilder.setX(value);
        changed.add("x");
    }

    public void setY(String value) {
        coordsBuilder.setY(value);
        changed.add("y");
    }

    public void setArea(String value) throws NotPositiveNumberException {
        try {
            long result = Long.parseLong(value);
            if (result <= 0) {
                throw new NotPositiveNumberException("Площадь квартиры должна быть целым числом больше нуля");
            } else {
                this.area = result;
                changed.add("area");
            }
        } catch (NumberFormatException e) {
            if (value.equals("")) {
                throw new NullPointerException("Поле площадь квартиры не может быть пустым!");
            } else {
                throw e;
            }
        }
    }

    public void setNumberOfRooms(String value) throws NotPositiveNumberException {
        try {
            Integer result = Integer.valueOf(value);
            if (result <= 0) {
                throw new NotPositiveNumberException("Число комнат в квартире должно быть целым положительным числом!");
            } else {
                this.numberOfRooms = result;
                changed.add("numberOfRooms");
            }
        } catch (NumberFormatException e) {
            if (value.equals("")) {
                this.numberOfRooms = null;
                changed.add("numberOfRooms");
            } else {
                throw e;
            }
        }
    }

    public void setCentralHeating(String value) throws BooleanInputException {
        if (value.equals("+")) {
            centralHeating = true;
            changed.add("centralHeating");
        } else if (value.equals("-")) {
            centralHeating = false;
            changed.add("centralHeating");
        } else {
            throw new BooleanInputException("Ожидалось +/-, встречено " + value);
        }
    }

    public void setView(String value) {
        try {
            this.view = View.valueOf(value);
            changed.add("view");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Поле вид не может принять значение " + value, e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Поле вид не может быть null");
        }
    }

    public void setTransport(String value) {
        try {
            this.transport = Transport.valueOf(value);
            changed.add("transport");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Поле транспорт не может принять значение " + value, e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Поле транспорт не может быть null");
        }
    }

    public void setHouseName(String value) {
        houseBuilder.setName(value);
        changed.add("nameHouse");
    }

    public void setYear(String value) throws NotPositiveNumberException {
        houseBuilder.setYear(value);
        changed.add("year");
    }

    public void setNumberOfFloors(String value) throws NotPositiveNumberException {
        houseBuilder.setNumberOfFloors(value);
        changed.add("numberOfFloors");
    }

    public void setCreationDate(java.time.ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void reset() {
        id = 0;
        name = null;
        coordinates = null;
        creationDate = null;
        area = 0;
        numberOfRooms = 0;
        centralHeating = false;
        view = null;
        transport = null;
        house = null;
        coordsBuilder.reset();
        houseBuilder.reset();
        changed = new HashSet<>();
    }

    public Flat getResult() { return new Flat(id, name, coordsBuilder.getResult(), area, numberOfRooms, centralHeating, view, transport, houseBuilder.getResult()); }

    public CoordinatesBuilder getCoordsBuilder() { return coordsBuilder; }

    public HouseBuilder getHouseBuilder() { return houseBuilder; }

    public HashSet<String> getChange() {
        return changed;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getArea() {
        return area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public boolean isCentralHeating() {
        return centralHeating;
    }

    public View getView() {
        return view;
    }

    public Transport getTransport() {
        return transport;
    }

    public House getHouse() {
        return house;
    }

    public double getX() { return coordsBuilder.getX(); }

    public Double getY() { return coordsBuilder.getY(); }

    public String getHouseName() { return houseBuilder.getName(); }

    public Long getYear() { return houseBuilder.getYear(); }

    public Integer getNumberOfFloors() { return houseBuilder.getNumberOfFloors(); }
}
