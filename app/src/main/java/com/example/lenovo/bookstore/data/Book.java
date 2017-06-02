package com.example.lenovo.bookstore.data;

/**
 * Created by lenovo on 6/1/2017.
 */

public class Book {
    private int id;
    private double price;
    private String title, imgURL, year;

    public Book(int id, String title, double price, String year) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.year = year;
        this.imgURL = null;
    }

    public String toString() {
        return title + " (" + price + ")";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getYear() {
        return year;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
