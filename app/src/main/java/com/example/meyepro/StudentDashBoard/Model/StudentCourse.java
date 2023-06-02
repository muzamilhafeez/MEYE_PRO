package com.example.meyepro.StudentDashBoard.Model;

public class StudentCourse  {
    private String teacherName;
    private String courseName;
    private String discipline;
    private String image;
    private double percentage;

    // Default constructor (required by some frameworks)
    public StudentCourse () {
    }

    // Parameterized constructor
    public StudentCourse (String teacherName, String courseName, String discipline, String image, int percentage) {
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.discipline = discipline;
        this.image = image;
        this.percentage = percentage;
    }

    // Getter and setter methods for the fields
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}