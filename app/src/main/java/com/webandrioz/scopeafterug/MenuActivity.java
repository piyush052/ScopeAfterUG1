package com.webandrioz.scopeafterug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.webandrioz.scopeafterug.models.Coachings;

public class MenuActivity extends AppCompatActivity {
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        id=getIntent().getStringExtra("id");

        Button books= (Button) findViewById(R.id.books);
        Button coachings= (Button) findViewById(R.id.coachings);
        Button papers= (Button) findViewById(R.id.paper);
        Button quiz= (Button) findViewById(R.id.quiz);
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MenuActivity.this,BooksActivity.class);
                in.putExtra("id",id);
                startActivity(in);

            }
        });
        coachings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MenuActivity.this,CoachingsActivity.class);
                in.putExtra("id",id);
                startActivity(in);

            }
        });

    }
}
