package com.example.lenovo.bookstore.data.book;

import android.content.Context;
import android.view.LayoutInflater;
import com.squareup.picasso.Picasso;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.bookstore.R;

import java.util.ArrayList;

/**
 * Created by thanawan on 6/4/2017 AD.
 */

public class BookDetail extends BaseAdapter implements BookItem{

    private ArrayList<Book> bookContent ;
    private LayoutInflater inf ;
    private Context con ;

    private ImageView bookCover ;
    private TextView bookName ;
    private TextView pubYear ;
    private TextView price ;

    public BookDetail(ArrayList<Book> bookContent ,Context con){
        this.bookContent = bookContent ;
        this.con = con ;
    }

    @Override
    public int getCount() {
        return bookContent.size();
    }

    @Override
    public Object getItem(int position) {
        return bookContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = convertView;
        if(convertView == null){
            inf = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inf.inflate(R.layout.book,null);
        }

        bookCover =(ImageView) layout.findViewById(R.id.book_cover);
        bookName =(TextView) layout.findViewById(R.id.book_name);
        price = (TextView) layout.findViewById(R.id.book_price);
        pubYear = (TextView) layout.findViewById(R.id.book_pub);

        setBookCover(position);
        setBookName(position);
        setBookPrice(position);
        setBookPub(position);

        return layout ;
    }

    @Override
    public void setBookCover(int local) {
        Picasso.with(con).load(bookContent.get(local).getImgURL()).into(this.bookCover);


    }

    @Override
    public void setBookName(int local) {
        bookName.setText(bookContent.get(local).getTitle());
    }

    @Override
    public void setBookPrice(int local) {
        price.setText(bookContent.get(local).getPrice()+"");
    }

    @Override
    public void setBookPub(int local) {
        pubYear.setText(bookContent.get(local).getYear());

    }
}
