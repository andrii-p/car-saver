package com.andriiP.carSaver.integration;

import com.andriiP.carSaver.dao.Car;
import com.andriiP.carSaver.dao.CarRepo;
import com.andriiP.carSaver.services.impl.CarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(CarServiceImpl.class)
@ActiveProfiles("dev")
public class SpringCarServiceTests {

    public static final Long CAR_ID = 1L;
    public static final Car car1 = new Car("Subaru Impreza 2012 Sport", "2012 Subaru Impreza", "Selling my Impreza");
    public static final Car car2 = new Car("Ford Focus 1999", "1999 Ford Focus", "Selling my Ford");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CarRepo carRepo;

    @Autowired
    private CarServiceImpl carService;

    @Before
    public void setUp(){

    }

    @Test
    public void find(){
        this.entityManager.persist(car1);
        Car car = carRepo.findByAdNameAndYearMakeModel("Subaru Impreza 2012 Sport", "2012 Subaru Impreza");
        assertNotNull(car);
    }

    @Test
    public void findByIdPositive(){
        carService.save(car2);
        Car car = carService.findByAdNameAndYearMakeModel("Ford Focus 1999", "1999 Ford Focus");
        assertNotNull(car);
    }

}
