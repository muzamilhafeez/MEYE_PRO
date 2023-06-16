package com.example.meyepro.DirectorDashBoard.Model;

public class TeacherCHRActivityDetails {
    String timein;
    String timeout;
    String sit;
    String stand;
//    int mobile;

    public TeacherCHRActivityDetails(String timein, String timeout, String sit, String stand) {
        this.timein = timein;
        this.timeout = timeout;
        this.sit = sit;
        this.stand = stand;
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



//    public int getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(int mobile) {
//        this.mobile = mobile;
//    }
}
