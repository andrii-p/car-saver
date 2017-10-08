package com.andriiP.carSaver.services;

import com.andriiP.carSaver.dao.Car;

import java.util.List;

/**
 * Created by kurt on 6/18/17.
 */
public interface CarService {

    public Car findById(Long id);

    public List<Car> findAll();

    public Car findByAdNameAndYearMakeModel(String adName, String yearMakeModel);

    public long countCars();

    public boolean exists(Long id);

    public void save(Car car);

    public void save(List<Car> cars);

    public void updateCarsViaRSS(String url);
}
