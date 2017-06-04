package com.example.lenovo.bookstore.list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bookstore.R;
import com.example.lenovo.bookstore.data.Book;

import java.util.ArrayList;

import static com.example.lenovo.bookstore.list.MainActivity.user;

/**
 * Created by lenovo on 6/4/2017.
 */

public class CartActivity extends AppCompatActivity {
    private TextView totalPriceText;

    Button purchase;
    ListView cartListView;

    ArrayAdapter<Book> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        cartListView = (ListView) findViewById(R.id.cart_list);
        adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, MainActivity.myCart);
        cartListView.setAdapter(adapter);

        getTotalPrice();
    }

    public void getTotalPrice() {

        purchase = (Button) findViewById(R.id.purchase);
        purchase.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        CartActivity.this);

                builder.setTitle("Confirm Add to CartActivity...");

                builder.setMessage("Are you sure you want add this book?");

                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(user.getTotalPrice() > user.getMoney()) {
                                    Toast.makeText(getApplicationContext(),
                                            "You have not enough money!", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Complete!", Toast.LENGTH_SHORT)
                                            .show();
                                    user.setMoney(user.getMoney() - user.getTotalPrice());
                                    user.clearCart();
                                }
                            }
                        });
                builder.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "You clicked on NO", Toast.LENGTH_SHORT)
                                        .show();
                                dialog.cancel();
                            }
                        });

                builder.show();
            }
        });
    }
}
