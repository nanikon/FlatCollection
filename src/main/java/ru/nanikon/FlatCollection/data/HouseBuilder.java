package ru.nanikon.FlatCollection.data;

import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;

/**
 * Builder House class. Checks the fields and creates an object
 */

public class HouseBuilder {
    private String name; //Поле может быть null
    private Long year; //Поле может быть null, Значение поля должно быть больше 0
    private Integer numberOfFloors; //Поле может быть null, Значение поля должно быть больше 0

    public void reset() {
        name = null;
        year = null;
        numberOfFloors = null;
    }

   public void setName(String name) {
        if (name.equals("")) {
            this.name = null;
        } else {
            this.name = name;
        }
   }

   public void setYear(String year) throws NotPositiveNumberException {
       try {
           Long result = Long.valueOf(year);
           if (result <= 0) {
               throw new NotPositiveNumberException("Год основания дома должен быть целым числом больше нуля");
           }
           this.year = result;
       } catch (NumberFormatException e) {
           if (year.equals("")){
               this.year = null;
           } else {
               throw new NumberFormatException("Поле год основания дома должно быть целым числом");
           }
       }
   }

   public void setNumberOfFloors(String numberOfFloors) throws NotPositiveNumberException {
       try {
           Integer result = Integer.valueOf(numberOfFloors);
           if (result <= 0) {
               throw new NotPositiveNumberException("Число квартир в доме должно быть целым числом больше нуля");
           }
           this.numberOfFloors = result;
       } catch (NumberFormatException e) {
           if (numberOfFloors.equals("")){
               this.numberOfFloors = null;
           } else {
               throw e;
           }
       }
   }

   public House getResult() {
        return new House(this.name, this.year, this.numberOfFloors);
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
