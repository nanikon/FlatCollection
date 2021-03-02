package ru.nanikon.FlatCollection.data;

public class House {
    private String name; //Поле может быть null
    private Long year; //Поле может быть null, Значение поля должно быть больше 0
    private Integer numberOfFloors; //Поле может быть null, Значение поля должно быть больше 0

    public House(String name, Long year, Integer numberOfFloors) {
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
    }

    public String getName() {
        return name;
    }

    public Long getYear() {
        return year;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }
}
