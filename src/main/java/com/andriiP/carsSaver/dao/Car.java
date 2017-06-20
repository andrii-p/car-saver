package com.andriiP.carsSaver.dao;

import com.andriiP.carsSaver.base.AbstractEntity;
import com.andriiP.carsSaver.enums.Condition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by kurt on 6/17/17.
 */
@Data
@Entity
public class Car extends AbstractEntity {

    private String title;
    private String VIN;
    private Long odometer;
    private String condition;
    private String cylinders;
    private String drive;
    private String fuel;
    private String paintColor;
    private String size;
    private String titleStatus;
    private String transmission;
    private String type;
    private String description;
    private String notes;

    //required by JPA
    public Car(){
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        return title.equals(car.title);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

}
