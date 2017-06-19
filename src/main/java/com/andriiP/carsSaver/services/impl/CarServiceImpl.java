package com.andriiP.carsSaver.services.impl;

import com.andriiP.carsSaver.dao.Car;
import com.andriiP.carsSaver.dao.CarRepo;
import com.andriiP.carsSaver.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurt on 6/18/17.
 */
@Service
public class CarServiceImpl implements CarService {

    private CarRepo carRepo;

    @Autowired
    public CarServiceImpl(CarRepo carRepo){
        this.carRepo = carRepo;
    }

    public Car findOne(Long id) {
        return carRepo.findOne(id);
    }

   public List<Car> findAll() {
       Iterable<Car> source = carRepo.findAll();
       List<Car> cars = new ArrayList<Car>();
       source.forEach(cars::add);
       return cars;
    }

   public long countCars() {
        return carRepo.count();
    }

    public boolean exists(Long id) {
        return carRepo.exists(id);
    }

    public void save(Car car) {
        carRepo.save(car);
    }

    public void save(List<Car> cars) {
        carRepo.save(cars);
    }
}
