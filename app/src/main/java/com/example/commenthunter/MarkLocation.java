package com.example.commenthunter;

public class MarkLocation {
    public double latitude;
    public double longitude;

    public MarkLocation() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public MarkLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
