package com.example.meyepro.DirectorDashBoard.Model;

public class TeacherCHRActivityDetails {
    String timein;
    String timeout;
    int sit;
    int stand;
    int mobile;
    public TeacherCHRActivityDetails(String timein, String timeout, int sit, int stand, int mobile) {
        this.timein = timein;
        this.timeout = timeout;
        this.sit = sit;
        this.stand = stand;
        this.mobile = mobile;
    }

    public String getTimein() {
        return timein;
    }

    public void setTimein(String timein) {
        this.timein = timein;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public int getSit() {
        return sit;
    }

    public void setSit(int sit) {
        this.sit = sit;
    }

    public int getStand() {
        return stand;
    }

    public void setStand(int stand) {
        this.stand = stand;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
}
