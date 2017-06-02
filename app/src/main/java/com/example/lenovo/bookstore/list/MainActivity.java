package com.example.lenovo.bookstore.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.lenovo.bookstore.R;
import com.example.lenovo.bookstore.data.Book;
import com.example.lenovo.bookstore.data.BookRepository;
import com.example.lenovo.bookstore.data.RemoteBookRepository;

import java.lang.reflect.Array;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements BookListView {

    BookListPresenter presenter;
    ArrayAdapter<Book> adapter;
    private ListView bookListView;

    EditText text;
    int id;

    RadioButton sortTitle, sortYear;
    Button searchTitleBtn, searchYearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = RemoteBookRepository.getInstance();

        bookListView = (ListView) findViewById(R.id.list_books);
        adapter = createAdapter(new ArrayList<Book>());
        bookListView.setAdapter(adapter);

        presenter = new BookListPresenter(repository, this);
        presenter.initialize();

        //searchTitleBtn = (Button) findViewById(R.id.searchTitleBtn);
    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books) {
        return new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books);
    }

    @Override
    public void setBookList(ArrayList<Book> books) {
        adapter = createAdapter(books);
        bookListView.setAdapter(adapter);
    }

    public void updateBook(ArrayList<Book> books) {
        adapter = createAdapter(books);
        bookListView.setAdapter(adapter);
    }

    public void search() {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")) {
                    presenter.initialize();
                } else {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void searchByTitle(String text) {
        ArrayList<Book> books = new ArrayList<Book>();
        for (Book book : presenter.books) {
            if (book.getTitle().toLowerCase().contains(text.toLowerCase())) {
                books.add(book);
            }
        }
        updateBook(books);
    }

    public void searchByYear(int year) {
        ArrayList<Book> books = new ArrayList<Book>();
        for (Book book : presenter.books) {
            if (book.getYear() == year) {
                books.add(book);
            }
        }
        updateBook(books);
    }

    public void sortByTitle() {
        final Collator collator = Collator.getInstance();
        adapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return collator.compare(o1.getTitle(), o2.getTitle());
            }
        });
    }

    public void sortByYear() {
        final Collator collator = Collator.getInstance();
        adapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return collator.compare(o1.getYear(), o2.getYear());
            }
        });
    }

    public void onClickSortTitle() {

    }
}
