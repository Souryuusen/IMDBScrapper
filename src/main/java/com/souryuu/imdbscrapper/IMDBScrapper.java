package com.souryuu.imdbscrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IMDBScrapper {

    private String htmlContent;

    private URL contentURL;

    public IMDBScrapper(String url) {
        try {
            this.contentURL = new URL(url);
            this.htmlContent = retrievePageContent(this.contentURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public IMDBScrapper(URL url) {
        this.contentURL = url;
        this.htmlContent = retrievePageContent(this.contentURL);
    }

    private String retrievePageContent(URL contentURL) {
        StringBuilder retrievedContentBuilder = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection) contentURL.openConnection();
            // Setting of connection params
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
            // Opening connection
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Reading Page HTML Content
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = br.readLine()) != null) {
                    retrievedContentBuilder.append(line).append(System.lineSeparator());
                }
            } else {
                // TODO: Dodanie wyjątku od otrzymania innego kodu HTTP
                throw new IOException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
