package com.andriiP.carsSaver.services.impl;

import com.andriiP.carsSaver.dao.Car;
import com.andriiP.carsSaver.dao.CarRepo;
import com.andriiP.carsSaver.services.CarService;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurt on 6/18/17.
 */
@Service
public class CarServiceImpl implements CarService {

    private Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private CarRepo carRepo;

    @Autowired
    public CarServiceImpl(CarRepo carRepo) {
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

    public Car findByTitleAndYearMakeModel(String title, String yearMakeModel){
        return carRepo.findByTitleAndYearMakeModel(title, yearMakeModel);
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


    /**
     * This method takes craigslist RSS feed url, parses it and extracts all car ads links.
     * Then it connects to each link, parses received HTML page and extracts all properties
     * needed for the Car object. If created Car object doesn't already exist in database then
     * it gets saved.     *
     * @param url RSS feed url
     */
    public void updateViaRSS(String url){

        if (url == null || url.isEmpty()) return;

        List<String> links = new ArrayList<>();

        //getting links for car ads from RSS feed
        try {
            logger.info("Readding RSS feed : " + url);

            URL RSSfeed = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(RSSfeed));

            feed.getEntries().forEach(entry -> links.add(entry.getLink()));
        } catch (Exception e){
            logger.error("ERROR on readubg RSS feed : " + url);
            logger.error("Message", e);
            return;
        }

        Document doc = null;

        //processing links
        for (String link : links){

            try{
                logger.info("Connecting to : " + link);
                doc = Jsoup.connect(link).get();
            } catch (Exception e){
                logger.error("ERROR on connecting to : " + url);
                logger.error("Message", e);
                continue;
            }

            //checking if ad wasn't deleted
            if (doc.getElementById("titletextonly") != null){

                String title = doc.getElementById("titletextonly").text();
                String price = doc.select("span.price").first() == null ? "" : doc.select("span.price").first().text();

                //removing parenthesis on both sides
                String location = doc.select("span.postingtitletext > small").first() == null ? "" : doc.select("span.postingtitletext > small").first().text();

                //removing irrelevant "QR Code Link to This Post" from the beginning
                String postBody = doc.getElementById("postingbody").text().substring(25).trim();

                Elements details = doc.select("p.attrgroup > span");
                String yearMakeModel = details.first().text();

                if (findByTitleAndYearMakeModel(title, yearMakeModel) == null){
                    logger.info("title - " + title + ", price - " + price + ", location - " + location +  ", postBody - " + postBody + ", yearMakeModel - " + yearMakeModel);

                    Car car = new Car(title, yearMakeModel, postBody);
                    car.setPrice(price);
                    car.setLocation(location);

                    //setting car properties
                    for (Element prop : details){
                        logger.info("<span> - " + prop.text());
                        setCarProp(car, prop.text());
                    }
                    logger.info("Saving : " + title + yearMakeModel);
                    save(car);
                } else {
                    logger.info("NOT SAVING - " + title + yearMakeModel);
                }

            }
        }
    }

    private void setCarProp(Car car, String prop){

        //breaking down string formatted as "key: value" and saving value
        if (prop.contains("VIN:")){;
            car.setVIN(prop.split(": ")[1]);
        } else if (prop.contains("odometer:")){
            car.setOdometer(prop.split(": ")[1]);
        } else if (prop.contains("condition:")){
            car.setCondition(prop.split(": ")[1]);
        } else if (prop.contains("cylinders:")){
            car.setCylinders(prop.split(": ")[1]);
        } else if (prop.contains("drive:")){
            car.setDrive(prop.split(": ")[1]);
        } else if (prop.contains("fuel:")){
            car.setFuel(prop.split(": ")[1]);
        } else if (prop.contains("paint color:")){
            car.setPaintColor(prop.split(": ")[1]);
        } else if (prop.contains("size:")){
            car.setSize(prop.split(": ")[1]);
        } else if (prop.contains("title status:")){
            car.setTitleStatus(prop.split(": ")[1]);
        } else if (prop.contains("transmission:")){
            car.setTransmission(prop.split(": ")[1]);
        } else if (prop.contains("type:")){
            car.setType(prop.split(": ")[1]);
        }
    }
}
