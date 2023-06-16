package com.example.meyepro.models;

public class DemoVideosResponse {
   String totalTimeIn, totalTimeOut;
         Float   sit;
         Float   stand;
         String   sitMin, standMin;

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

    public Float getSit() {
        return sit;
    }

    public void setSit(Float sit) {
        this.sit = sit;
    }

    public Float getStand() {
        return stand;
    }

    public void setStand(Float stand) {
        this.stand = stand;
    }

    public String getSitMin() {
        return sitMin;
    }

    public void setSitMin(String sitMin) {
        this.sitMin = sitMin;
    }

    public String getStandMin() {
        return standMin;
    }

    public void setStandMin(String standMin) {
        this.standMin = standMin;
    }
}
