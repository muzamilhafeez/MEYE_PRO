package com.example.meyepro.models;

public class Attendance {

    private int id;
    private int enrollId;
    private String date;
    private boolean status;
    private String name;

    public Attendance(int id, int enrollId, String date, boolean status, String name) {
        this.id = id;
        this.enrollId = enrollId;
        this.date = date;
        this.status = status;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(int enrollId) {
        this.enrollId = enrollId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
