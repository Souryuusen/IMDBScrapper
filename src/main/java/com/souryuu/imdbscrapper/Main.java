package com.souryuu.imdbscrapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL("https://www.imdb.com/title/tt2911666/");

            URLConnection connection = url.openConnection();
            connection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line=br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            String s1 = sb.toString();

            String directors = s1.substring(s1.indexOf("Directors"), s1.indexOf("Writer"));

            List<String> directorList = new ArrayList<>();

            while(directors.contains("tt_ov_dr")) {
                directors = directors.substring(directors.indexOf("tt_ov_dr")+10);
                System.out.println(directors.substring(0, directors.indexOf("</a></li>")));
                directorList.add(directors.substring(0, directors.indexOf("</a></li>")));
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\TEST\\test.txt")));
            bw.write(directorList.stream().toString());
            bw.flush();
            bw.close();
            br.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(sb != null && sb.length() > 0) {
            //System.out.println(sb.toString());
        }
    }

}
