package com.souryuu.imdbscrapper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class IMDBScrapper {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";

    private Document htmlContent;

    public IMDBScrapper(String url) {
        try {
            Connection.Response response = Jsoup.connect(url).userAgent(USER_AGENT).referrer("www.google.com").followRedirects(true).execute();
            if(response.statusCode() == 200) {
                this.htmlContent = response.parse();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document getHtmlContent() {
        return this.htmlContent;
    }

    public void printContent() {
        System.out.println(this.htmlContent.toString());
    }
}
