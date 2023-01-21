package com.example.meyepro.models;

public class Venue {
   int ID ;

    public Venue(int ID, String NAME) {
        this.ID = ID;
        this.NAME = NAME;
    }
    public Venue() {

    }
    String NAME ;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

}
