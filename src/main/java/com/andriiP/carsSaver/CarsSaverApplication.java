package com.andriiP.carsSaver;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

			System.out.println("READING RSS feed - " + url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(url));

			feed.getEntries().forEach(entry -> links.add(entry.getLink()));
			//String link = links.get(0);
			for (String link : links){
				System.out.println("===============================================================");
				System.out.println("Connecting to " + link);
				Document doc = Jsoup.connect(link).get();

				System.out.println("HTML doc");
				//System.out.println(doc);
				System.out.println("===============================================================");

				Element aElem= doc.select("a.showcontact").first();
				System.out.println("contact link - " + aElem);

				if (aElem != null){
					String contactUrl = url.getProtocol() + "://" + url.getHost() + doc.select("a.showcontact").attr("href");
					System.out.println("contactUrl - " + contactUrl);

					Document contactInfo = Jsoup.connect(contactUrl).get();
					System.out.println("contactInfo page:");
					System.out.println(contactInfo);
					Element body = contactInfo.getElementsByTag("body").first();
					System.out.println("body:");
					System.out.println(body.text());
					System.out.println("===============================================================");
				}

				/*Elements paragraphs = doc.select("p.attrgroup");

				System.out.println("Paragraphs:");
				for (Element e : paragraphs){
					System.out.println("<p> - " + e);
					for (Element s : e.select("span")){
						System.out.println("span - " + s.text());
					}
				}*/

				System.out.println("===============================================================");
				Elements spans = doc.select("p.attrgroup > span");
				for (Element e : spans){
					System.out.println("<span> - " + e.text());
				}
			}

			//System.out.println(entries);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}