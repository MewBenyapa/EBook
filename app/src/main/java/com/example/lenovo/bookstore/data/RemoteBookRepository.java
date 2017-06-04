package com.example.lenovo.bookstore.data;

import android.os.AsyncTask;

import com.example.lenovo.bookstore.data.book.Book;
import com.example.lenovo.bookstore.data.book.BookRepository;
import com.example.lenovo.bookstore.data.decoder.BookJSONDecoder;
import com.example.lenovo.bookstore.utils.UrlFetcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 6/1/2017.
 */

public class RemoteBookRepository extends BookRepository {

    private List<Book> books;

    private static RemoteBookRepository instance = null;

    public static RemoteBookRepository getInstance() {
        if (instance == null) {
            instance = new RemoteBookRepository();
        }
        return instance;
    }

    private RemoteBookRepository() {
        books = new ArrayList<Book>();
    }

    @Override
    public void fetchAllBooks() {
        BookFetcherTask task = new BookFetcherTask();
        task.execute();
     }

    @Override
    public List<Book> getAllBook() {
        return books;
    }

    public class BookFetcherTask extends AsyncTask<Void, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookList = loadJSON();
            if (bookList != null) {
                return BookJSONDecoder.createListFromJSONStr(bookList);
            } else {
                return null;
            }
        }

        private String loadJSON() {
            URL bookURL = null;
            try {
                bookURL = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return (new UrlFetcher(bookURL)).fetch();
        }

        @Override
        protected void onPostExecute(ArrayList<Book> book) {
            if(book != null){
                books.clear();
                books.addAll(book);
                setChanged();
                notifyObservers();
            }
            super.onPostExecute(book);
        }
    }
}
