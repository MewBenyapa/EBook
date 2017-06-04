package com.example.lenovo.bookstore.list;

import com.example.lenovo.bookstore.data.Book;

import java.util.ArrayList;

/**
 * Created by lenovo on 6/1/2017.
 */

public interface BookListView {
    public void updateBook(ArrayList<Book> books);
    public void sortByTitle();
}
