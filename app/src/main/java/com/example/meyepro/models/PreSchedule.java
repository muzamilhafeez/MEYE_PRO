package com.example.meyepro.models;

public class PreSchedule {
    private int id;
    private int timeTableId;
    private String date;
    private String day;
    private String starttime;
    private String endtime;
    private String venueName;
    private boolean status;
    // Constructor
//    public PreSchedule(int id, int timeTableId, String date, String day, String startTime,
//                      String endTime, String venueName, boolean status) {
//        this.id = id;
//        this.timeTableId = timeTableId;
//        this.date = date;
//        this.day = day;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.venueName = venueName;
//        this.status = status;
//    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
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
        return starttime;
    }

    public void setStartTime(String startTime) {
        this.starttime = startTime;
    }

    public String getEndTime() {
        return endtime;
    }

    public void setEndTime(String endTime) {
        this.endtime = endTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
