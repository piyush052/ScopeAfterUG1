package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.webandrioz.scopeafterug.BooksActivity;
import com.webandrioz.scopeafterug.BranchesActivity;
import com.webandrioz.scopeafterug.CoachingsActivity;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.models.Book;
import com.webandrioz.scopeafterug.models.Coachings;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by root on 26/2/17.
 */

public class CoachingListAdapter  extends ArrayAdapter<String> {
    Context con;
    ArrayList<Coachings> books=new ArrayList<>();

    public CoachingListAdapter(CoachingsActivity examsActivity, ArrayList<Coachings> exams) {
        super(examsActivity, R.layout.coachings_listview_adapter);
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
        convertView =li.inflate(R.layout.coachings_listview_adapter,null);
        TextView author= (TextView) convertView.findViewById(R.id.basic);
        TextView buyonline= (TextView) convertView.findViewById(R.id.website);

        TextView name= (TextView) convertView.findViewById(R.id.contact);
        author.setText(books.get(position).getWebsite());
        name.setText(books.get(position).getName()+"\n"+books.get(position).getAbout()+"\n"+books.get(position).getAddress());
        buyonline.setText(books.get(position).getConatct());

        Button map= (Button) convertView.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.valueOf(books.get(position).getLatitude()),Float.valueOf(books.get(position).getLongitude()));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                con.startActivity(intent);

            }
        });

        buyonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(books.get(position).getWebsite()));
                con.startActivity(i);
            }
        });




        return convertView;
    }
}
