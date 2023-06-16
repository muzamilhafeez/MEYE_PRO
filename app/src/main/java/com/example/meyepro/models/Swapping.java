package com.example.meyepro.models;

public class Swapping {
   int id, timeTableIdFrom, timeTableIdTo;
          String  date, day, startTime, endTime;
          Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeTableIdFrom() {
        return timeTableIdFrom;
    }

    public void setTimeTableIdFrom(int timeTableIdFrom) {
        this.timeTableIdFrom = timeTableIdFrom;
    }

    public int getTimeTableIdTo() {
        return timeTableIdTo;
    }

    public void setTimeTableIdTo(int timeTableIdTo) {
        this.timeTableIdTo = timeTableIdTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}
