package ru.skillbox.dowloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebScraper {
    private final String url;

    public WebScraper(String url) {
        this.url = url;
    }

    public Set<String> getImageLinks() {
        HashSet<String> links = new HashSet<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements images = doc.select("img");
            for (Element image : images) {
                links.add(image.attr("abs:src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }
}
