package com.example.appsforgood;

public class Stops {
    String stopName;
    int stopCode;
    double longitude, latitude;


    public Stops(){
        stopCode = -1;
        stopName = "";
        latitude = -1;
        longitude = -1;
    }

    public Stops(int code, String name, double stopLong, double stopLat){
        stopCode = code;
        stopName = name;
        latitude = stopLat;
        longitude = stopLong;
    }

    public String getStopName() {
        return stopName;
    }

    public int getStopCode() { return stopCode; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getArrivalTimes() {
        String times = "";
        return times;
    }
    public String display(){
        return stopName;
    }
}
