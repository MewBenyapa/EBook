package com.example.lenovo.bookstore.data.book;

import java.util.List;
import java.util.Observable;

/**
 * Created by lenovo on 6/1/2017.
 */

public abstract class BookRepository extends Observable {
    public abstract void fetchAllBooks();
    public abstract List<Book> getAllBook();
}
