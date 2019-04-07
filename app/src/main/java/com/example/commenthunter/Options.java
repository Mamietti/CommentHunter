package com.example.commenthunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

    private Button addcomment;
    private Button showcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        addcomment = findViewById(R.id.button);
        showcomment = findViewById(R.id.button1);

        addcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcommentActivity();
            }
        });

        showcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcommentActivity();
            }
        });
    }

    private void addcommentActivity(){
        Intent intent = new Intent(Options.this, AddMessage.class);
        startActivity(intent);

    }

    private void showcommentActivity(){
        Intent intent = new Intent(Options.this, AddMessage.class);
        startActivity(intent);

    }




}
