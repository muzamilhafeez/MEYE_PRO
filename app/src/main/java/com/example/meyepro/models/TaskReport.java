package com.example.meyepro.models;

import java.util.ArrayList;

public class TaskReport {
    private int id;
    private String courseName;
    private String day;
    private String discipline;
    private String startTime;
    private String endTime;
    private String totalTimeIn;
    private String totalTimeOut;
    private String status;
    private String date;
    private String teacherName;
    private String image;
    private String sit;
    private String stand;
    private String venue;
    int teacherSlotId;
    Boolean isShown;

    public Boolean getShown() {
        return isShown;
    }

    public void setShown(Boolean shown) {
        isShown = shown;
    }

    ArrayList<TeacherCHRActivityDetails>  teacherCHRActivityDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
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

    public String getTotalTimeIn() {
        return totalTimeIn;
    }

    public void setTotalTimeIn(String totalTimeIn) {
        this.totalTimeIn = totalTimeIn;
    }

    public String getTotalTimeOut() {
        return totalTimeOut;
    }

    public void setTotalTimeOut(String totalTimeOut) {
        this.totalTimeOut = totalTimeOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSit() {
        return sit;
    }

    public void setSit(String sit) {
        this.sit = sit;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getTeacherSlotId() {
        return teacherSlotId;
    }

    public void setTeacherSlotId(int teacherSlotId) {
        this.teacherSlotId = teacherSlotId;
    }

    public ArrayList<TeacherCHRActivityDetails> getTeacherCHRActivityDetails() {
        return teacherCHRActivityDetails;
    }

    public void setTeacherCHRActivityDetails(ArrayList<TeacherCHRActivityDetails> teacherCHRActivityDetails) {
        this.teacherCHRActivityDetails = teacherCHRActivityDetails;
    }
}
