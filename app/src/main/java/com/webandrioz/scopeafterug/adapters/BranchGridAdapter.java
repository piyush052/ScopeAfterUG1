package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.webandrioz.scopeafterug.BranchesActivity;
import com.webandrioz.scopeafterug.DomainActivity;
import com.webandrioz.scopeafterug.ExamsActivity;
import com.webandrioz.scopeafterug.MenuActivity;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.models.Branch;
import com.webandrioz.scopeafterug.models.Domains;

import java.util.ArrayList;

/**
 * Created by root on 26/2/17.
 */

public class BranchGridAdapter  extends BaseAdapter {


    Context con;
    ArrayList<Branch> domain=new ArrayList<>();
    static int pos;

    public BranchGridAdapter(BranchesActivity domainActivity, ArrayList<Branch> domain) {
        con=domainActivity;
        this.domain=domain;

    }




    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return domain.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    public class Holder
    {
        TextView tv;
        ImageView img;
//        CardView cd;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.domian_grid_layout,null);
        BranchGridAdapter.Holder holder=new BranchGridAdapter.Holder();
        pos=position;

//       holder.cd= (CardView) convertView.findViewById(R.id.card_viewHome);
//       convertView.findViewById(R.id.card_viewHome).setBackgroundColor(Color.parseColor("#ffffff"));
        holder.img= (ImageView) convertView.findViewById(R.id.domainGridViewImage);
        //holder.img.setVisibility(View.INVISIBLE);
        holder.tv= (TextView) convertView.findViewById(R.id.domainGridText);

//        holder.img.setImageResource(images[position]);
        holder.tv.setText(domain.get(position).getNamen());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(con, MenuActivity.class);
                in.putExtra("id",domain.get(position).getId());
                con.startActivity(in);
            }
        });



        return convertView;

    }
}
