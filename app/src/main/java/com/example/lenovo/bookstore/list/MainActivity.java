package com.example.lenovo.bookstore.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private GridView bookListView;

    EditText text;
    int btnID;

    Button sortTitle, sortYear;
    Button searchTitleBtn, searchYearBtn;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = RemoteBookRepository.getInstance();

        bookListView = (GridView) findViewById(R.id.book_grid);
        adapter = createAdapter(new ArrayList<Book>());
        bookListView.setAdapter(adapter);

        presenter = new BookListPresenter(repository, this);
        presenter.initialize();

        searchTitleBtn = (Button) findViewById(R.id.btn_search_by_title);
        searchYearBtn = (Button) findViewById(R.id.btn_search_by_year);

        sortTitle = (Button) findViewById(R.id.title);
        sortYear = (Button) findViewById(R.id.publish);
        radioGroup = (RadioGroup) findViewById(R.id.sort_radioGroup);

        search();
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
        btnID = searchTitleBtn.getId();

        text = (EditText) findViewById(R.id.search);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")) {
                    presenter.initialize();
                } else {
                    if (btnID == searchTitleBtn.getId()) {
                        searchByTitle(s.toString());
                    } else if (btnID == searchYearBtn.getId()){
                        searchByYear(s.toString());
                    }
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

    public void searchByYear(String year) {
        ArrayList<Book> books = new ArrayList<Book>();
        for ( Book book : presenter.books){
            if ( book.getYear().contains(year)){
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

    public void radioSort(View view) {
    }

//    public void getAllBooks(View view) {
//        presenter.initialize();
//    }

}
