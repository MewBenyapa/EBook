package com.example.lenovo.bookstore.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bookstore.R;
import com.example.lenovo.bookstore.data.User;
import com.example.lenovo.bookstore.data.book.Book;
import com.example.lenovo.bookstore.data.book.BookDetail;

import java.util.ArrayList;

import static com.example.lenovo.bookstore.list.MainActivity.myCart;

/**
 * Created by lenovo on 6/4/2017.
 */

public class CartActivity extends AppCompatActivity {
    private TextView totalPriceText;
    double total;

    private TextView currentBalance;
    double totalBalance;

    User user = new User();

    EditText fund;
    Button purchase;
    ListView cartListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        totalPriceText = (TextView) findViewById(R.id.num_total_price);
        totalPriceText.setText("" + setTotalPrice(MainActivity.myCart));

        currentBalance = (TextView) findViewById(R.id.num_balance);
        currentBalance.setText("" + totalBalance);

        cartListView = (ListView) findViewById(R.id.cart_list);
        BookDetail cartAdapt = new BookDetail(myCart, CartActivity.this);
        cartListView.setAdapter(cartAdapt);

        fund = (EditText) findViewById(R.id.add_balance);

        TotalPrice();
    }

    public void TotalPrice() {

        purchase = (Button) findViewById(R.id.purchase);
        purchase.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        CartActivity.this);

                builder.setTitle("Confirm Buying...");

                builder.setMessage("Are you sure you want buy all in your cart?");

                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(total > totalBalance) {
                                    Toast.makeText(getApplicationContext(),
                                            "You have not enough money!", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Complete!", Toast.LENGTH_SHORT)
                                            .show();
                                    currentBalance.setText("" + (totalBalance - total));
                                    for(Book b : myCart) {
                                        user.addBook(b);
                                    }
                                    myCart.clear();
                                    Intent i=new Intent(
                                            CartActivity.this,
                                            MainActivity.class);
                                    startActivity(i);
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

    public double setTotalPrice(ArrayList<Book> cartList) {
        for (Book book : cartList) {
            total += book.getPrice();
        }
        return total;
    }

    public void addBalance(View view) {
        String fundStr = fund.getText().toString();
        double newFund = Double.parseDouble(fundStr);
        totalBalance += user.getBalance() + newFund;
        currentBalance.setText("" + totalBalance);
    }
}
