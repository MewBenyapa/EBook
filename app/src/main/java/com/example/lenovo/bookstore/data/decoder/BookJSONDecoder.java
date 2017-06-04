package com.example.lenovo.bookstore.data.decoder;

import com.example.lenovo.bookstore.data.book.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 6/1/2017.
 */

public class BookJSONDecoder {
    public static ArrayList<Book> createListFromJSONStr(String str) {
        ArrayList<Book> results = new ArrayList<Book>();
        try {
            JSONArray jsonArray = new JSONArray(str);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject bookJson = jsonArray.getJSONObject(i);
                Book book = new Book(bookJson.getInt("id"), bookJson.getString("title"),
                        bookJson.getDouble("price"), bookJson.getString("pub_year"),
                bookJson.getString("img_url"));
                if (book != null)
                    results.add(book);
            }
            return results;
        } catch (JSONException e) {
            return null;
        }

    }

}
