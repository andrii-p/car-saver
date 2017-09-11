package com.andriiP.carsSaver;

import com.andriiP.carsSaver.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarsSaverApplication {

	@Autowired
	private CarService carService;

	public static void main(String[] args) {
		SpringApplication.run(CarsSaverApplication.class, args);

	/*	if (carService == null){
			System.out.println("carServiceImp is NULL");
		}

		carService.updateCarsViaRSS("https://denver.craigslist.org/search/cto?format=rss&hasPic=1&max_price=20000&min_auto_year=2012&postal=80228&query=subaru%20impreza&search_distance=75");
	*/
	}
}
