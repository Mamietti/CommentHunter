package com.example.commenthunter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class AddMessage extends AppCompatActivity {

    Button saveButton;
    EditText messageText;

    double latitude;
    double longitude;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        saveButton = findViewById(R.id.saveButton);
        messageText = findViewById(R.id.messageText);
        database = FirebaseDatabase.getInstance();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the message from EditText
                String message = messageText.getText().toString();

                //Do nothing if the string is empty
                if (!message.equals("")) {
                    //get db reference at the root
                    final DatabaseReference myRef = database.getReference();
                    //a hack to be able to query uniquely a location easily with firebase, by using lat+a+long as unique key in db
                    //also we need to replace . with , since firebase does not allow it inside key
                    final String uniquekey = (String.valueOf(latitude) + "a" + String.valueOf(longitude)).replace(".", ",");
                    //check if there is this location already exist in marklocations (key is lat+"a"+long)
                    myRef.child("marklocations").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.child(uniquekey).exists()) {
                                //Not exist yet, create new mark location
                                MarkLocation newMark = new MarkLocation(latitude, longitude);
                                myRef.child("marklocations").child(uniquekey).setValue(newMark);
                                Log.d("DB", "Write new location");
                            } else {
                                Log.d("DB", "Location of this mark existed");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("DB", "Can't connect to DB");
                        }
                    });

                    //Get time and create new Message object with it
                    long timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                    Message newMessage = new Message(message, timeStamp);
                    //Create new child under the uniquekey under messages and set to the new Message
                    myRef.child("messages").child(uniquekey).push().setValue(newMessage);
                    Log.d("DB", "Add new message");

                    //TODO: Currently it wil go back to the map after adding message, perhaps going to show messages is better?
                    Intent intent = new Intent(AddMessage.this, GMapActivity.class);
                    startActivity(intent);
                }
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
            Log.d("ADDMSG_ACTIVITY", "Latitude="+latitude+" Longitude="+longitude);
        }
        else {
            Log.d("ADDMSG_ACTIVITY", "Unable to retrieve coordinate");
        }

    }
}
