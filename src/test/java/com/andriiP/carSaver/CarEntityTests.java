package com.andriiP.carSaver;

import com.andriiP.carSaver.dao.Car;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CarEntityTests {

    private Car testCarPositive;
    private Car testCarNegative;

    @Before
    public void setUp(){
        testCarPositive = new Car("Subaru Outback 2012 Sport", "2012 Subaru Outback", "Selling my car");
        testCarNegative = new Car("Ford Mustang 1900", "1900 Ford Mustand", "Selling my Ford");
    }

    @Test
    public void setCarAttrPositive(){
        testCarPositive.setCarAttribute("VIN: 123");
        assertEquals(testCarPositive.getVIN(), "123");

        testCarPositive.setCarAttribute("odometer: 100000");
        assertEquals(testCarPositive.getOdometer(), "100000");

        testCarPositive.setCarAttribute("condition: good");
        assertEquals(testCarPositive.getCondition(), "good");

        testCarPositive.setCarAttribute("cylinders: 4");
        assertEquals(testCarPositive.getCylinders(), "4");

        testCarPositive.setCarAttribute("drive: fwd");
        assertEquals(testCarPositive.getDrive(), "fwd");

        testCarPositive.setCarAttribute("fuel: gas");
        assertEquals(testCarPositive.getFuel(), "gas");

        testCarPositive.setCarAttribute("paint color: white");
        assertEquals(testCarPositive.getPaintColor(), "white");

        testCarPositive.setCarAttribute("size: compact");
        assertEquals(testCarPositive.getSize(), "compact");

        testCarPositive.setCarAttribute("title status: clean");
        assertEquals(testCarPositive.getTitleStatus(), "clean");

        testCarPositive.setCarAttribute("transmission: automatic");
        assertEquals(testCarPositive.getTransmission(), "automatic");

        testCarPositive.setCarAttribute("type: wagon");
        assertEquals(testCarPositive.getType(), "wagon");
    }

    @Test
    public void setCarAttrNegative(){
        testCarNegative.setCarAttribute("VIN : 123");
        assertNull(testCarNegative.getVIN());

        testCarNegative.setCarAttribute("odo: 100000");
        assertNull(testCarNegative.getOdometer());

        testCarNegative.setCarAttribute("condition - good");
        assertNull(testCarNegative.getCondition());

        testCarPositive.setCarAttribute("cylinders:: 4");
        assertNull(testCarNegative.getCylinders());

        testCarPositive.setCarAttribute("some random text");
        assertNull(testCarNegative.getDrive());

        testCarPositive.setCarAttribute("fuel:gas");
        assertNull(testCarNegative.getFuel());;
    }
}
