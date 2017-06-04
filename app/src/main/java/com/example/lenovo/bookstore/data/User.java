package com.example.lenovo.bookstore.data;

import java.util.ArrayList;

/**
 * Created by lenovo on 6/4/2017.
 */

public class User {
    private double money;
    private ArrayList<Book> cart;
    private ArrayList<Book> myBookList;

    public User() {
        this.money = 0;
        cart = new ArrayList<Book>();
        myBookList = new ArrayList<Book>();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<Book> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Book> cart) {
        this.cart = cart;
    }

    public ArrayList<Book> getMyBookList() {
        return myBookList;
    }

    public void addBook(Book book) {
        cart.add(book);
    }

    public double getTotalPrice() {
        double price = 0;
        for(Book book : cart) {
            price += book.getPrice();
        }
        return price;
    }


    public boolean clearCart() {
        if(money >= getTotalPrice()) {
            money -= getTotalPrice();
            myBookList.addAll(cart);
            cart.clear();
            return true;
        } else {
            return false;
        }
    }
}
