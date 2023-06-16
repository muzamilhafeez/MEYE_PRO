package com.example.meyepro.models;

public class TimeTableRules {
    private int id;
    private String discipline;
    private String starttime;
    private String endtime;
    private String day;
    private String courseCode;
    private String courseName;
    private String venue;
    private String teacherName;
    private boolean isSelected;
    private String sessionId;
    private String sessionName;

    // Constructor
    public TimeTableRules(int id, String discipline, String startTime, String endTime, String day,
                          String courseCode, String courseName, String venue, String teacherName,
                          boolean isSelected, String sessionId, String sessionName) {
        this.id = id;
        this.discipline = discipline;
        this.starttime = startTime;
        this.endtime = endTime;
        this.day = day;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.venue = venue;
        this.teacherName = teacherName;
        this.isSelected = isSelected;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}
