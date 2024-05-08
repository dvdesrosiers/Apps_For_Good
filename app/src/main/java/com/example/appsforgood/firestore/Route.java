package com.example.appsforgood.firestore;

import java.util.ArrayList;

public class Route {
    ArrayList <String> routeLog;

    int routeNum, numStops;

    public Route(ArrayList<String> inputRoutes){
        routeLog.add(String.valueOf(inputRoutes));
    }

    public int getRouteNum(ArrayList<String> routeX) {
        routeNum = Integer.parseInt(routeX.get(0));
        return routeNum;
    }

    public int getNumStops(ArrayList<String> routeX) {
        numStops = routeX.size()-1;
        return numStops;
    }
}
