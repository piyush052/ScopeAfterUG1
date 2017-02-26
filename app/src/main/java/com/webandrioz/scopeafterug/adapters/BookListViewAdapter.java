package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webandrioz.scopeafterug.BooksActivity;
import com.webandrioz.scopeafterug.BranchesActivity;
import com.webandrioz.scopeafterug.ExamsActivity;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.models.Book;
import com.webandrioz.scopeafterug.models.Exams;

import java.util.ArrayList;

/**
 * Created by root on 26/2/17.
 */

public class BookListViewAdapter extends ArrayAdapter<String> {
    Context con;
    ArrayList<Book> books=new ArrayList<>();

    public BookListViewAdapter(BooksActivity examsActivity, ArrayList<Book> exams) {
        super(examsActivity, R.layout.book_listview_layout);
        con=examsActivity;
        books=exams;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return books.get(position).getName();
    }

//    @Override
//    public int getPosition(@Nullable String item) {
//        return super.getPosition(item);
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.book_listview_layout,null);
        TextView author= (TextView) convertView.findViewById(R.id.author);
        TextView buyonline= (TextView) convertView.findViewById(R.id.buyOnline);

        TextView name= (TextView) convertView.findViewById(R.id.bookName);
        name.setText(books.get(position).getName());
        author.setText(books.get(position).getAuthor());
        buyonline.setText(books.get(position).getBuy());
        buyonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(books.get(position).getBuy()));
                con.startActivity(i);
            }
        });




        return convertView;
    }
}
