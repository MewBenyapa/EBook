package com.example.lenovo.bookstore.list;

import com.example.lenovo.bookstore.data.book.Book;
import com.example.lenovo.bookstore.data.book.BookRepository;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by lenovo on 6/1/2017.
 */

public class BookListPresenter implements Observer {

    private BookListView view;
    private BookRepository repository;

    ArrayList<Book> books;

    public BookListPresenter(BookRepository repo, BookListView view) {
        this.repository = repo;
        this.view = view;
        books = new ArrayList<>();
    }

    public void initialize() {
        repository.addObserver(this);
        repository.fetchAllBooks();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == repository) {
            books = new ArrayList<Book>(repository.getAllBook());
            view.updateBook(books);
            view.sortByTitle();
        }
    }
}
