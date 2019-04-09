package com.example.commenthunter;

public class Message {
    public long timeStamp;
    public String message;

    public Message(){

    }

    public Message(String message, long timeStamp){
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
