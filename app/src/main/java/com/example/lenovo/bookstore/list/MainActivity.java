package com.example.lenovo.bookstore.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.bookstore.R;
import com.example.lenovo.bookstore.data.Book;
import com.example.lenovo.bookstore.data.BookDetail;
import com.example.lenovo.bookstore.data.BookRepository;
import com.example.lenovo.bookstore.data.RemoteBookRepository;
import com.example.lenovo.bookstore.data.User;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements BookListView {

    BookListPresenter presenter;
    ArrayAdapter<Book> adapter;
    private BookDetail book ;
    private GridView bookListView;

    public static ArrayList<Book> myCart = new ArrayList<Book>();

    public static User user = new User();

    EditText text;

    Button sortTitle, sortYear, cart;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = RemoteBookRepository.getInstance();

        bookListView = (GridView) findViewById(R.id.book_grid);
        adapter = createAdapter(new ArrayList<Book>());
        book = new BookDetail(new ArrayList<Book>(),MainActivity.this);
        bookListView.setAdapter(book);


        presenter = new BookListPresenter(repository, this);
        presenter.initialize();

        sortTitle = (Button) findViewById(R.id.title);
        sortYear = (Button) findViewById(R.id.publish);
        radioGroup = (RadioGroup) findViewById(R.id.sort_radioGroup);

        // cart = (Button) findViewById(R.id.)

        search();
    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books) {
        return new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books);
    }

    @Override
    public void setBookList(ArrayList<Book> books) {
        book = new BookDetail(new ArrayList<Book>(),MainActivity.this);
        bookListView.setAdapter(book);
    }

    public void updateBook(ArrayList<Book> books) {
        book = new BookDetail(books,MainActivity.this);
        bookListView.setAdapter(book);
    }

    public void search() {

        text = (EditText) findViewById(R.id.search);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")) {
                    presenter.initialize();
                } else {
                    searchText(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void searchText(String text) {
        ArrayList<Book> books = new ArrayList<Book>();
        for (Book book : presenter.books) {
            if (book.getTitle().toLowerCase().contains(text.toLowerCase())) {
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

    public void radioSortTitle(View view) {
        if (((RadioButton) view).isChecked()) {
            Toast.makeText(this, "Title", Toast.LENGTH_SHORT).show();
            sortByTitle();
        } else {
            searchText(text.getText().toString());
        }
    }

    public void radioSortYear(View view) {
        if (((RadioButton) view).isChecked()) {
            Toast.makeText(this, "Publication Years", Toast.LENGTH_SHORT).show();
            sortByYear();
        } else {
            searchText(text.getText().toString());
        }
    }



//    public void getAllBooks(View view) {
//        presenter.initialize();
//    }


}
