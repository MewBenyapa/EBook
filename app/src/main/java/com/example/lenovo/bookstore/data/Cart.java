package com.example.lenovo.bookstore.data;

import android.content.Context;

import com.example.lenovo.bookstore.data.book.Book;

import java.util.ArrayList;

/**
 * Created by lenovo on 6/4/2017.
 */

public class Cart {

    private ArrayList<Book> cartList = new ArrayList<Book>();
    private double balance;

    public Cart(Context context) {
        this.balance = 0;
    }

    public ArrayList<Book> getListbook() {
        return cartList;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }
}
