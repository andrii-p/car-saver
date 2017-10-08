package com.andriiP.carSaver.services.impl;

import com.andriiP.carSaver.dao.Car;
import com.andriiP.carSaver.dao.CarRepo;
import com.andriiP.carSaver.services.CarService;
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

    public void updateCarsViaRSS(String rss){
        if (rss == null || rss.isEmpty()) return;

        List<String> links;
        List<Document> htmlFiles;
        List<Car> cars;

        try {
            links = getLinksFromRSS(rss);
            htmlFiles = getHTMLsFromLinks(links);
            cars = parseHTMLsAndGetCars(htmlFiles);
            cars = checkForDuplicates(cars);
            save(cars);
        } catch (Exception e){
            logger.error("ERROR on either processing RSS feed, links or html files: ");
            logger.error("ERROR message:", e);
        }
    }

    private List<String> getLinksFromRSS(String rss) throws Exception{
        logger.info("Reading RSS feed : " + rss);

        URL RSSfeed = new URL(rss);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(RSSfeed));

        List<String> links = new ArrayList<>();
        feed.getEntries().forEach(entry -> links.add(entry.getLink()));
        return links;
    }

    private List<Document> getHTMLsFromLinks(List<String> links) throws Exception {
        Document html;
        List<Document> htmlFiles = new ArrayList<>();

        for (String link : links){
            logger.info("Processing link : " + link);
            html = Jsoup.connect(link).get();
            htmlFiles.add(html);
        }
        return htmlFiles;
    }

    private List<Car> parseHTMLsAndGetCars(List<Document> htmlFiles) {
        List<Car> cars = new ArrayList<>();

        for (Document html : htmlFiles) {
            //checking if ad wasn't deleted
            if (html.getElementById("titletextonly") != null) {
                Elements carAttributes = html.select("p.attrgroup > span");

                String adName = html.getElementById("titletextonly").text();
                String yearMakeModel = carAttributes.first().text();
                String postBody = html.getElementById("postingbody").text();
                postBody = postBody.substring(25).trim(); //removing irrelevant "QR Code Link to This Post" phrase from the beginning of post body

                if (postBody.length() >= 1500) { //database field restriction is 1500 characters
                    postBody = postBody.substring(0, 1500);
                }

                Car car = new Car(adName, yearMakeModel, postBody);

                for (Element attr : carAttributes) {
                    car.setCarAttribute(attr.text());
                }

                String price = html.select("span.price").first() == null ? null : html.select("span.price").first().text();
                String location = html.select("span.postingtitletext > small").first() == null ? null : html.select("span.postingtitletext > small").first().text();

                if (location != null) { //removing parenthesis around
                    location = location.substring(1, location.length() - 1);
                }

                car.setPrice(price);
                car.setLocation(location);

                cars.add(car);
            }
        }
        return cars;
    }

    private List<Car> checkForDuplicates(List<Car> cars){
        List<Car> newCars = new ArrayList<>();
        for(Car car : cars){
            if (findByAdNameAndYearMakeModel(car.getAdName(), car.getYearMakeModel()) == null){
                logger.info("FOUND NEW CAR : \n" + car);
                newCars.add(car);
            } else {
                logger.info("FOUND A DUPLICATE: \n" + car);
            }
        }
        return newCars;
    }

    public Car findById(Long id) {
        return carRepo.findOne(id);
    }

    public List<Car> findAll() {
        Iterable<Car> source = carRepo.findAll();
        List<Car> cars = new ArrayList<Car>();
        source.forEach(cars::add);
        return cars;
    }

    public Car findByAdNameAndYearMakeModel(String adName, String yearMakeModel){
        return carRepo.findByAdNameAndYearMakeModel(adName, yearMakeModel);
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

}
