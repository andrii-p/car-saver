package com.andriiP.carSaver.controllers;

import com.andriiP.carSaver.dao.Car;
import com.andriiP.carSaver.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by kurt on 6/18/17.
 */
@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private CarService carService;
    private Logger logger = LoggerFactory.getLogger(AppController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/consumeRSS", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public void processRSS(@RequestBody String url) {
        logger.info(" > POST /api/consumeRSS");

        carService.updateCarsViaRSS(url);

        logger.info(" < POST /api/consumeRSS");
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getCars() {
        logger.info("GET /api/cars");
        return this.carService.getCars();
    }


}
