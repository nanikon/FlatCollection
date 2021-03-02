package ru.nanikon.FlatCollection.data;

/**
 * Builder Coordinates class. Checks the fields and creates an object
 */
public class CoordinatesBuilder {
    private double x;
    private Double y;

    public void setX(String x) {
        try {
            this.x = Double.parseDouble(x);
        } catch (NumberFormatException e) {
            if (x.equals("")) {
                throw new NullPointerException("Поле координата х не может быть пустым!");
            } else {
                throw e;
            }
        }
    }

    public void setY(String y) {
        try {
            this.y = Double.valueOf(y);
        } catch (NumberFormatException e) {
            if (y.equals("")) {
                throw new NullPointerException("Поле координата y не может быть пустым!");
            } else {
                throw e;
            }
        }
    }

    public Coordinates getResult() {
        return new Coordinates(x, y);
    }

    public void reset() {
        x = 0;
        y = null;
    }

    public double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
