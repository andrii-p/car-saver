package com.andriiP.carSaver;

import com.andriiP.carSaver.dao.Car;
import com.andriiP.carSaver.dao.CarRepo;
import com.andriiP.carSaver.services.impl.CarServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTests {

    public static final Long CAR_ID = 1L;
    public static final Car car1 = new Car("Subaru Outback 2012 Sport", "2012 Subaru Outback", "Selling my car");
    public static final Car car2 = new Car("Ford Mustang 1900", "1900 Ford Mustand", "Selling my Ford");

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepo carRepo;

    @Test
    public void findByIdPositive(){
        when(carRepo.findOne(anyLong())).thenReturn(new Car());
        Car car = carService.getById(CAR_ID);
        assertNotNull(car);
    }

    @Test
    public void findByIdNegative(){
        when(carRepo.findOne(anyLong())).thenReturn(null);
        Car car = carService.getById(99L);
        assertNull(car);
    }

    @Test
    public void findAllPositive(){
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        when(carRepo.findAll()).thenReturn(cars);
        List<Car> result = carService.getCars();
        assertEquals(result.size(), 2);
    }

    @Test
    public void findAlldNegative(){
        when(carRepo.findAll()).thenReturn(new ArrayList<>());
        List<Car> result = carService.getCars();
        assertEquals(result.size(), 0);
    }
}
