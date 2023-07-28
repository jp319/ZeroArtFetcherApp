package com.jp319.zeroartfetcher.testing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupWebScrapingExample {
    public static void main(String[] args) {
        String url = "https://www.zerochan.net/Genshin+Impact";

        try {

            Document document = Jsoup.connect(url).get();
            String title = document.title();
            System.out.println("Page Title: " + title);

            Element linkElement = document.select("head link[rel=alternate][type=application/json]").first();

            if (linkElement != null) {
                String hrefValue = linkElement.attr("href");
                System.out.println("Href attribute value: " + hrefValue);
            } else {
                System.out.println("Link not found.");
            }

            // Extracting specific data from the HTML content
            Elements imageElements = document.select("img.preview");
            for (Element imageElement : imageElements) {
                String imageUrl = imageElement.attr("src");
                System.out.println("Image URL: " + imageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
