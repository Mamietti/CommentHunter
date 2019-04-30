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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
