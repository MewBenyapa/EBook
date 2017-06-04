package com.example.lenovo.bookstore.list;

import com.example.lenovo.bookstore.data.book.Book;

import java.util.ArrayList;

/**
 * Created by lenovo on 6/1/2017.
 */

public interface BookListView {
    void updateBook(ArrayList<Book> books);
    void sortByTitle();
}
