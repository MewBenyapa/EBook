package com.example.lenovo.bookstore.data.decoder;

import com.example.lenovo.bookstore.data.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 6/1/2017.
 */

public class BookJSONDecoder {
    public static Book createFromJSONObject(JSONObject o) {
        try {
            Book book = new Book(o.getInt("id"), o.getString("title"),
                                 o.getDouble("price"), o.getInt("year"));
                 book.setImgURL(o.getString("img_url"));
                 return book;
        } catch (JSONException e) {
            return null;
        }
    }

    public static ArrayList<Book> createListFromJSONStr(String str) {
        ArrayList<Book> results = new ArrayList<Book>();

        try {
            JSONArray jsonArray = new JSONArray(str);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject bookJson = jsonArray.getJSONObject(i);
                Book book = createFromJSONObject(bookJson);
                if (book != null) {
                    results.add(book);
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return results;
    }

}
