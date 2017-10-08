package com.andriiP.carSaver.integration;

import com.andriiP.carSaver.dao.Car;
import com.andriiP.carSaver.dao.CarRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("dev")
public class SpringCarRepoTests {

    public static final Long CAR_ID = 1L;
    public static final Car car1 = new Car("Subaru Outback 2012 Sport", "2012 Subaru Outback", "Selling my car");
    public static final Car car2 = new Car("Ford Mustang 1900", "1900 Ford Mustand", "Selling my Ford");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CarRepo carRepo;

    @Before
    public void setUp(){
        carRepo.save(car1);
    }

    @Test
    public void findPositive(){
        Car car = carRepo.findByAdNameAndYearMakeModel("Subaru Outback 2012 Sport", "2012 Subaru Outback");
        assertNotNull(car);
    }

    @Test
    public void findNegative(){
        Car car = carRepo.findByAdNameAndYearMakeModel("Random text", "Random text");
        assertNull(car);
    }

    @Test
    public void savePositive(){
        carRepo.save(car2);
        Car car = carRepo.findByAdNameAndYearMakeModel("Ford Mustang 1900", "1900 Ford Mustand");
        assertNotNull(car);
    }
}
