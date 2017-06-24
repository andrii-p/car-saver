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

    public void updateViaRSS(String url){

        if (url == null || url.isEmpty()) return;

        List<String> links = new ArrayList<>();

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

        for (String link : links){

            try{
                logger.info("Connecting to : " + link);
                doc = Jsoup.connect(link).get();
            } catch (Exception e){
                logger.error("ERROR on connecting to : " + url);
                logger.error("Message", e);
                continue;
            }

            String title = doc.getElementById("titletextonly").text();
            String price = doc.select("span.price").first() == null ? "" : doc.select("span.price").first().text();
            String location = doc.select("span.postingtitletext > small").first() == null ? "" : doc.select("span.postingtitletext > small").first().text();
            String postBody = doc.getElementById("postingbody").text().substring(25).trim();

            Elements details = doc.select("p.attrgroup > span");
            String yearMakeModel = details.first().text();

            if (findByTitleAndYearMakeModel(title, yearMakeModel) != null){
                logger.info("title - " + title + ", price - " + price + ", location - " + location +  ", postBody - " + postBody + ", yearMakeModel - " + yearMakeModel);

                Car car = new Car(title, yearMakeModel, postBody);
                car.setPrice(price);
                car.setLocation(location);

                for (Element prop : details){
                    logger.info("<span> - " + prop.text());
                    setCarProp(car, prop.text());
                }
            }

        }
    }

    private void setCarProp(Car car, String prop){
        String[] keyValue;

        if (prop.contains("VIN:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("odometer:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("condtion:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("cylinders:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("drive:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("fuel:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("paint color:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("size :")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("title status:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("transmission:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        } else if (prop.contains("type:")){
            keyValue = prop.split(": ");
            car.setVIN(keyValue[1]);
        }
    }
}
