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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
