package com.example.lenovo.bookstore.data;

import com.example.lenovo.bookstore.data.book.Book;
import com.example.lenovo.bookstore.data.book.BookRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 6/1/2017.
 */

public class MockUpBookRepo extends BookRepository {
    private List<Book> books;
    private static MockUpBookRepo instance = null;

    public static MockUpBookRepo getInstance() {
        if (instance == null) {
            instance = new MockUpBookRepo();
        }
        return instance;
    }

    private MockUpBookRepo() {
        books = new ArrayList<Book>();
//        books.add(new Book(1, "Introduction to Java", 13.95, "2015"));
//        books.add(new Book(10, "Introduction to C++", 19.95, "2016"));
//        books.add(new Book(12, "Algorithms", 29.95, "2012"));
//        books.add(new Book(17, "Pascal Programming", 17.95, "2007"));
    }

    @Override
    public void fetchAllBooks() {
        setChanged();
        notifyObservers();
    }

    @Override
    public List<Book> getAllBook() {
        return books;
    }
}
