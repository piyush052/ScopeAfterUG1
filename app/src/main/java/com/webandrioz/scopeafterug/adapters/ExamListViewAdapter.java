package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webandrioz.scopeafterug.BranchesActivity;
import com.webandrioz.scopeafterug.ExamsActivity;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.models.Exams;

import java.util.ArrayList;

/**
 * Created by root on 26/2/17.
 */

public class ExamListViewAdapter extends ArrayAdapter<String> {
    Context con;
    ArrayList<Exams> exams=new ArrayList<>();

    public ExamListViewAdapter(ExamsActivity examsActivity, ArrayList<Exams> exams) {
        super(examsActivity,R.layout.exam_listview_layout);
        con=examsActivity;
        this.exams=exams;
    }

    @Override
    public int getCount() {
        return exams.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return exams.get(position).getAbout();
    }

//    @Override
//    public int getPosition(@Nullable String item) {
//        return super.getPosition(item);
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.exam_listview_layout,null);
        TextView name= (TextView) convertView.findViewById(R.id.examsTextView);
        name.setText(exams.get(position).getExamsName()+"\n\n"+exams.get(position).getAbout());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(con, BranchesActivity.class);
                in.putExtra("id",exams.get(position).getExamsId());
                con.startActivity(in);
            }
        });




        return convertView;
    }
}
