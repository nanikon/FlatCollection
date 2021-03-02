package ru.nanikon.FlatCollection.data;

public class Coordinates {
    private double x;
    private Double y; //Поле не может быть null

    Coordinates(double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
