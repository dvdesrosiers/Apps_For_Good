package com.example.appsforgood;

public class BusRoutes {

    String routeName;
    int image;

    public BusRoutes(String routeName, int image) {
        this.routeName = routeName;
        this.image = image;
    }

    public String getRouteName() {
        return routeName;
    }


    public int getImage() {
        return image;
    }
}