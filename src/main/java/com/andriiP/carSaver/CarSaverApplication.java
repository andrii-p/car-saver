package com.andriiP.carSaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarSaverApplication {

	//@Autowired
	//private CarService carService;

	public static void main(String[] args) {
		SpringApplication.run(CarSaverApplication.class, args);

	/*	if (carService == null){
			System.out.println("carServiceImp is NULL");
		}

		carService.updateCarsViaRSS("https://denver.craigslist.org/search/cto?format=rss&hasPic=1&max_price=20000&min_auto_year=2012&postal=80228&query=subaru%20impreza&search_distance=75");
	*/
	//curl -X POST http://localhost:8080/api/processRSS -d "https://denver.craigslist.org/search/cto?format=rss&hasPic=1&max_price=20000&min_auto_year=2012&postal=80228&query=subaru%20impreza&search_distance=75" -H "Content-Type:text/plain"
	}
}
