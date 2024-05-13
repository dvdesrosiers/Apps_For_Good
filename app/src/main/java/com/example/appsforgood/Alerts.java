package com.example.appsforgood;

public class Alerts {
    
    // Declare instance variables for alert properties
    String time, date, id, issue;

    // Default constructor (empty)
    public Alerts(){

    }
    
    // Getter method for retrieving the time of the alert
    public String getTime() {
        return time;
    }

    // Getter method for retrieving the date of the alert
    public String getDate() {
        return date;
    }

    // Getter method for retrieving the ID of the alert
    public String getId() { return id; }

    // Getter method for retrieving the issue description of the alert
    public String getIssue() {
        return issue;
    }
    
    // Setter method for setting the time of the alert
    public void setTime(String time) {
        this.time = time;
    }
    
    //Setter method for setting the date of the alert 
    public void setDate(String date) {
        this.date = date;
    }
    
    // Setter method for setting the ID of the alert
    public void setId(String id) { this.id = id; }

    // Setter method for setting the issue description of the alert
    public void setIssue(String issue) {
        this.issue = issue;
    }
}
