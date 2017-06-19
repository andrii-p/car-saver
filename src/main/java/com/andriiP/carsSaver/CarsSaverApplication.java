package com.andriiP.carsSaver;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.util.List;

@SpringBootApplication
public class CarsSaverApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsSaverApplication.class, args);

		try {
			URL url = new URL("https://denver.craigslist.org/search/cto?format=rss");

			System.out.println("READING URL - " + url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(url));

			List<SyndEntry> entries = feed.getEntries();

			for (SyndEntry entry : entries){
				System.out.println(entry.getLink());
				System.out.println("===============================================================");
			}

			//System.out.println(entries);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
