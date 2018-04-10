package com.andriiP.carSaver.controllers;

import com.andriiP.carSaver.dao.Car;
import com.andriiP.carSaver.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

        String defaultRSS = "https://denver.craigslist.org/search/cto?format=rss&max_price=20000&min_auto_year=2012&postal=80228&query=subaru%20impreza&search_distance=75";

        if (url.trim().isEmpty()) {
            url = defaultRSS;
        }

        carService.updateCarsViaRSS(url);

        logger.info(" < POST /api/consumeRSS");
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getCars() {
        logger.info("GET /api/cars");
        return this.carService.getCars();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SimpleGrantedAuthority> getUserRoles() {
        logger.info("GET /api/roles");
        return (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }


}
