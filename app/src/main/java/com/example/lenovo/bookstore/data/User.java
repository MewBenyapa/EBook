package com.example.lenovo.bookstore.data;

import com.example.lenovo.bookstore.data.book.Book;

import java.util.ArrayList;

/**
 * Created by lenovo on 6/4/2017.
 */

public class User {
    private double balance;
    private ArrayList<Book> cart;
    private ArrayList<Book> myBookList;

    public User() {
        this.balance = 0;
        cart = new ArrayList<Book>();
        myBookList = new ArrayList<Book>();
    }

    public double getBalance() { return balance; }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Book> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Book> cart) { this.cart = cart; }

    public ArrayList<Book> getMyBookList() {
        return myBookList;
    }

    public void addBook(Book book) {
        cart.add(book);
    }

}
