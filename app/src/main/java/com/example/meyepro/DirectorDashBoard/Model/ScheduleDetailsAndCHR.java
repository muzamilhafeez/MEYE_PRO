package com.example.meyepro.DirectorDashBoard.Model;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDetailsAndCHR {
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
//    private String mobile;

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
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }

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

    public ArrayList<TeacherCHRActivityDetails> getTeacherCHRActivityDetails() {
        return teacherCHRActivityDetails;
    }

    public void setTeacherCHRActivityDetails(ArrayList<TeacherCHRActivityDetails> teacherCHRActivityDetails) {
        this.teacherCHRActivityDetails = teacherCHRActivityDetails;
    }

    private ArrayList<TeacherCHRActivityDetails> teacherCHRActivityDetails;

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

    public ScheduleDetailsAndCHR(int id, String courseName, String day, String discipline, String startTime, String endTime, String totalTimeIn, String totalTimeOut, String status, String date, String teacherName, String image, String sit, String stand, String venue, ArrayList<TeacherCHRActivityDetails> teacherCHRActivityDetails) {
        this.id = id;
        this.courseName = courseName;
        this.day = day;
        this.discipline = discipline;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTimeIn = totalTimeIn;
        this.totalTimeOut = totalTimeOut;
        this.status = status;
        this.date = date;
        this.teacherName = teacherName;
        this.image = image;
        this.sit = sit;
        this.stand = stand;
        this.venue = venue;
        this.teacherCHRActivityDetails = teacherCHRActivityDetails;
    }
}

