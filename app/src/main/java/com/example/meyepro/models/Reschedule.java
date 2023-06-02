package com.example.meyepro.models;

public class Reschedule {
    private int id;
    private int teacherSlotId;
    private String date;
    private String day;
    private String starttime;
    private String endtime;
    private String venueName;
    private boolean status;

    // Constructors
    public Reschedule() {
    }

    public Reschedule(int id, int teacherSlotId, String date, String day, String starttime, String endtime, String venueName, boolean status) {
        this.id = id;
        this.teacherSlotId = teacherSlotId;
        this.date = date;
        this.day = day;
        this.starttime = starttime;
        this.endtime = endtime;
        this.venueName = venueName;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherSlotId() {
        return teacherSlotId;
    }

    public void setTeacherSlotId(int teacherSlotId) {
        this.teacherSlotId = teacherSlotId;
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
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
