package com.example.lenovo.bookstore.list;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.view.Menu;
import com.example.lenovo.bookstore.R;
import com.example.lenovo.bookstore.data.book.Book;
import com.example.lenovo.bookstore.data.book.BookDetail;
import com.example.lenovo.bookstore.data.book.BookRepository;
import com.example.lenovo.bookstore.data.RemoteBookRepository;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements BookListView {

    BookListPresenter presenter;
    public static BookDetail book;

    private GridView bookListView;

    public static ArrayList<Book> myCart = new ArrayList<Book>();

    EditText text;

    public MainActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = RemoteBookRepository.getInstance();

        bookListView = (GridView) findViewById(R.id.book_grid);
        presenter = new BookListPresenter(repository, this);
        presenter.initialize();

        bookListView.setOnItemClickListener(onItemClickListener);
        search();

    }

    public void updateBook(ArrayList<Book> books) {
        book = new BookDetail(books, MainActivity.this);
        bookListView.setAdapter(book);
    }


        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this);

                builder.setTitle("Confirm Add to Cart...");

                builder.setMessage("Are you sure you want to this book in your cart?");

                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Complete", Toast.LENGTH_SHORT)
                                        .show();
                                myCart.add((Book) bookListView.getItemAtPosition(position));
                            }
                        });
                builder.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Cancel", Toast.LENGTH_SHORT)
                                        .show();
                                dialog.cancel();
                            }
                        });

                builder.show();
            }
        };

    public void search() {

        text = (EditText) findViewById(R.id.search);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == null) {
                    presenter.initialize();
                } else {
                    searchTextTitle(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void searchTextTitle(String text) {
        ArrayList<Book> books = new ArrayList<Book>();
        for (Book book : presenter.books) {
            if ((book.getTitle().toLowerCase().contains(text.toLowerCase())) || book.getYear().contains(text)) {
                books.add(book);
            }
        }
        updateBook(books);
    }

    public void sortByTitle() {
        ArrayList<Book> books = new ArrayList<>();
        books.addAll(presenter.books);
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
            }
        });
        updateBook(books);
    }

    public void sortByYear() {
        ArrayList<Book> books = new ArrayList<>();
        books.addAll(presenter.books);
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Integer.parseInt(o1.getYear()) < Integer.parseInt(o2.getYear())? 1 : 0;
            }
        });
        updateBook(books);
    }

    public void radioSortTitle(View view) {
        if (((RadioButton) view).isChecked()) {
            Toast.makeText(this, "Title", Toast.LENGTH_SHORT).show();
            sortByTitle();
        } else {
            searchTextTitle(text.getText().toString());
        }
    }

    public void radioSortYear(View view) {
        if (((RadioButton) view).isChecked()) {
            Toast.makeText(this, "Publication Years", Toast.LENGTH_SHORT).show();
            sortByYear();
        } else {
            searchTextTitle(text.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.cart_menu:
                Intent cartIntent = new Intent(this, CartActivity.class);
                this.startActivity(cartIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
