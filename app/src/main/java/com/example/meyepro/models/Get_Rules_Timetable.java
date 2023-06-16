package com.example.meyepro.models;

import java.util.ArrayList;

public class Get_Rules_Timetable {
 Boolean   startRecord, midRecord, endRecord, fullRecord;
 ArrayList<TimeTableRules> data;

    public Boolean getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Boolean startRecord) {
        this.startRecord = startRecord;
    }

    public Boolean getMidRecord() {
        return midRecord;
    }

    public void setMidRecord(Boolean midRecord) {
        this.midRecord = midRecord;
    }

    public Boolean getEndRecord() {
        return endRecord;
    }

    public void setEndRecord(Boolean endRecord) {
        this.endRecord = endRecord;
    }

    public Boolean getFullRecord() {
        return fullRecord;
    }

    public void setFullRecord(Boolean fullRecord) {
        this.fullRecord = fullRecord;
    }

    public ArrayList<TimeTableRules> getData() {
        return data;
    }

    public void setData(ArrayList<TimeTableRules> data) {
        this.data = data;
    }
}

