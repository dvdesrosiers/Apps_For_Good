package com.example.appsforgood;

public class Stops {
    String stopName;
    int stopCode, routeNumber;


    public Stops(){
        stopCode = -1;
        stopName = "";
        routeNumber = -1;
    }
    
    public Stops(int code, String name, int route){
        stopCode = code;
        stopName = name;
        routeNumber = route;
    }

    public String getStopName() {
        return stopName;
    }

    public int getStopCode() { return stopCode; }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }
}
