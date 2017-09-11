package com.andriiP.carsSaver.controllers;

import com.andriiP.carsSaver.services.CarService;
import com.andriiP.carsSaver.services.impl.CarServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kurt on 6/18/17.
 */
@Controller
public class HomeController {

    @Autowired
    private CarService carService;
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/api/processRSS", method = RequestMethod.POST, consumes = "text/plain")
    public void processRSS(@RequestBody String url){
        carService.updateCarsViaRSS(url);
    }


}
