package com.example.commenthunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MessagesActivity extends AppCompatActivity {

    double latitude;
    double longitude;
    FirebaseDatabase database;

    private ArrayList<String> arrayOfMessages;
    private MessagesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        database = FirebaseDatabase.getInstance();


        arrayOfMessages = new ArrayList<>();
        adapter = new MessagesAdapter(getApplicationContext(), arrayOfMessages);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

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
            Log.d("MESSAGES_ACTIVITY", "Latitude=" + latitude + " Longitude=" + longitude);
        } else {
            Log.d("MESSAGES_ACTIVITY", "Unable to retrieve coordinate");
        }

        final DatabaseReference myRef = database.getReference();


        final String uniquekey = (String.valueOf(latitude) + "a" + String.valueOf(longitude)).replace(".", ","); //.child(uniquekey)
        myRef.child("messages").child(uniquekey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ArrayList<String> arrayOfMessages = new ArrayList<>();
                Log.d("DB", "Connected");
                arrayOfMessages.clear(); //remove all old messages
                for (DataSnapshot comment: dataSnapshot.getChildren()) {
                    //loop child and add to array
                    String message = comment.child("message").getValue(String.class);
                    Log.d("Messages", message);
                    arrayOfMessages.add(message);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DB", "Can't connect to DB");
            }


        });

    }

}
