package com.andriiP.carSaver.dao;

import com.andriiP.carSaver.base.AbstractEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by kurt on 6/17/17.
 */
@Data
@Entity
public class Car extends AbstractEntity {

    private String adName;
    private String price;
    private String location;
    private String yearMakeModel;
    private String VIN;
    private String odometer;
    @Column(name="carCondition") //condition is a reserved word in MySQL
    private String condition;
    private String cylinders;
    private String drive;
    private String fuel;
    private String paintColor;
    private String size;
    private String titleStatus;
    private String transmission;
    private String type;
    @Column(length = 1500)
    private String postBody;
    private String notes;

    //required by JPA
    public Car(){
        super();
    }
    public Car (String adName, String yearMakeModel, String postBody ){
        super();
        this.adName = adName;
        this.yearMakeModel = yearMakeModel;
        this.postBody = postBody;
    }

    public void setCarAttribute(String prop){

        //breaking down string formatted as "key: value" and saving value
        if (prop.contains("VIN: ")){;
            this.setVIN(prop.split(": ")[1]);
        } else if (prop.contains("odometer: ")){
            this.setOdometer(prop.split(": ")[1]);
        } else if (prop.contains("condition: ")){
            this.setCondition(prop.split(": ")[1]);
        } else if (prop.contains("cylinders: ")){
            this.setCylinders(prop.split(": ")[1]);
        } else if (prop.contains("drive: ")){
            this.setDrive(prop.split(": ")[1]);
        } else if (prop.contains("fuel: ")){
            this.setFuel(prop.split(": ")[1]);
        } else if (prop.contains("paint color: ")){
            this.setPaintColor(prop.split(": ")[1]);
        } else if (prop.contains("size: ")){
            this.setSize(prop.split(": ")[1]);
        } else if (prop.contains("title status: ")){
            this.setTitleStatus(prop.split(": ")[1]);
        } else if (prop.contains("transmission: ")){
            this.setTransmission(prop.split(": ")[1]);
        } else if (prop.contains("type: ")){
            this.setType(prop.split(": ")[1]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        return adName.equals(car.adName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (adName != null ? adName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Ad name: ").append(this.adName).append(", Year Make Model: ").append(this.yearMakeModel);

        if (this.price != null) sb.append(", Price: ").append(this.price);
        if (this.location != null) sb.append(", Location: ").append(this.location);
        if (this.VIN != null) sb.append(", VIN: ").append(this.VIN);
        if (this.odometer != null) sb.append(", Odometer: ").append(this.odometer);
        if (this.condition != null) sb.append(", Condition: ").append(this.condition);
        if (this.cylinders != null) sb.append(", Cylinders: ").append(this.cylinders);
        if (this.drive != null) sb.append(", Drive: ").append(this.drive);
        if (this.fuel != null) sb.append(", Fuel: ").append(this.fuel);
        if (this.paintColor != null) sb.append(", Paint Color: ").append(this.paintColor);
        if (this.size != null) sb.append(", Size: ").append(this.size);
        if (this.titleStatus != null) sb.append(", Title: ").append(this.titleStatus);
        if (this.transmission != null) sb.append(", Transmission: ").append(this.transmission);
        if (this.type != null) sb.append(", Type: ").append(this.type);

        return sb.toString();
    }

}
