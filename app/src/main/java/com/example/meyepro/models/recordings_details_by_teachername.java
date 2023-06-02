package com.example.meyepro.models;

public class recordings_details_by_teachername {
    private String courseCode;
    private String courseName;
    private String teacherName;
    private String discipline;
    private String venue;
    private String day;
    private String startTime;
    private String endTime;
    private String date;
    private String status;
    private int slot;
    private String fileName;
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public recordings_details_by_teachername(String courseCode, String courseName, String teacherName, String discipline, String venue, String day, String startTime, String endTime, String date, String status, int slot, String fileName, String thumbnail) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.discipline = discipline;
        this.venue = venue;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.status = status;
        this.slot = slot;
        this.fileName = fileName;
        this.thumbnail = thumbnail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getVenue() {
        return venue;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public int getSlot() {
        return slot;
    }

    public String getFileName() {
        return fileName;
    }
}
