package com.example.lenovo.bookstore.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lenovo on 6/1/2017.
 */

public class UrlFetcher {
    private final URL url;

    public UrlFetcher(URL url) {
        this.url = url;
    }

    public String fetch() {
        String result = "";
        try {
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()
            ));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (IOException e) {
            return null;
        }
    }
}
