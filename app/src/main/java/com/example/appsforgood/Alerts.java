package com.example.appsforgood;

public class Alerts {
    String time, date, id, issue;

    public Alerts(){

    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getId() { return id; }

    public String getIssue() {
        return issue;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) { this.id = id; }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}