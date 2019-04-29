package com.example.commenthunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

    private Button addcomment;
    private Button showcomment;

    //This hold the position of the marker clicked that start this activity
    double latitude;
    double longitude;

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

    @Override
    protected void onResume() {
        super.onResume();
        //Retrieve the coordinate
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");
            Log.d("OPTION_ACTIVITY", "Latitude=" + latitude + " Longitude=" + longitude);
        } else {
            Log.d("OPTION_ACTIVITY", "Unable to retrieve coordinate");
        }

    }


    private void addcommentActivity() {
        Intent intent = new Intent(Options.this, AddMessage.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);

    }

    private void showcommentActivity() {
        //Start the show message activity?
        Intent intent = new Intent(Options.this, MessagesActivity.class);
        startActivity(intent);
    }


}
