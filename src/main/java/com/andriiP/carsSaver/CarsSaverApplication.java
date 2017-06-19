package com.andriiP.carsSaver;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CarsSaverApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsSaverApplication.class, args);

		List<String> links = new ArrayList<>();

		try {
			URL url = new URL("https://denver.craigslist.org/search/cto?format=rss");

			System.out.println("READING URL - " + url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(url));

			feed.getEntries().forEach(entry -> links.add(entry.getLink()));

			for (SyndEntry entry : feed.getEntries()){
				System.out.println(entry.getLink());
				System.out.println("===============================================================");
			}

			Document doc = Jsoup.connect(links.get(0)).get();
			System.out.println(doc);

			//System.out.println(entries);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
