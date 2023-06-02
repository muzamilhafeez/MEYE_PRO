package com.example.meyepro.models;

public class Rules {
    private int id;
    private int timeTableId;
    private int startRecord;
    private int midRecord;
    private int endRecord;
    private int fullRecord;
    public Rules(){

    }
    public Rules(int id, int timeTableId, int startRecord, int midRecord, int endRecord, int fullRecord) {
        this.id = id;
        this.timeTableId = timeTableId;
        this.startRecord = startRecord;
        this.midRecord = midRecord;
        this.endRecord = endRecord;
        this.fullRecord = fullRecord;
    }

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

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getMidRecord() {
        return midRecord;
    }

    public void setMidRecord(int midRecord) {
        this.midRecord = midRecord;
    }

    public int getEndRecord() {
        return endRecord;
    }

    public void setEndRecord(int endRecord) {
        this.endRecord = endRecord;
    }

    public int getFullRecord() {
        return fullRecord;
    }

    public void setFullRecord(int fullRecord) {
        this.fullRecord = fullRecord;
    }
}

