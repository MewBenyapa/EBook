package com.example.lenovo.bookstore.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.bookstore.R;

/**
 * Created by lenovo on 6/4/2017.
 */

public class UserActivity extends AppCompatActivity {

    TextView balance;
    EditText addBalance;

    protected void onCreat(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        balance = (TextView) findViewById(R.id.balance);
        addBalance = (EditText) findViewById(R.id.edit_balance);
    }
}
