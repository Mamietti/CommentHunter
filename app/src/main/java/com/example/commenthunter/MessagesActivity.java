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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");
            Log.d("OPTION_ACTIVITY", "Latitude=" + latitude + " Longitude=" + longitude);
        } else {
            Log.d("OPTION_ACTIVITY", "Unable to retrieve coordinate");
        }
        final String uniquekey = (String.valueOf(latitude) + "a" + String.valueOf(longitude)).replace(".", ","); //.child(uniquekey)
        myRef.child("messages").child(uniquekey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Message> arrayOfMessages = new ArrayList<>();
                MessagesAdapter adapter = new MessagesAdapter(getApplicationContext(), arrayOfMessages);

                Iterator<com.google.firebase.database.DataSnapshot> posts = dataSnapshot.getChildren().iterator();
                while (posts.hasNext()){
                    Message post = posts.next().getValue(Message.class);
                    System.out.println(post.message);
                    adapter.add(post);
                }

                // Construct the data source

                ListView listView = findViewById(R.id.commentListView);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DB", "Can't connect to DB");
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
            Log.d("MESSAGES_ACTIVITY", "Latitude=" + latitude + " Longitude=" + longitude);
        } else {
            Log.d("MESSAGES_ACTIVITY", "Unable to retrieve coordinate");
        }

    }

}
