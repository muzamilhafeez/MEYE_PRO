package com.example.meyepro.models;

import android.graphics.Bitmap;

public class MEYE_USER {
   int ID ;
   String UID ;
    String NAME ;
     String  PASS ;
    Bitmap IMAGE;
    String ROLE ;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public Bitmap getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(Bitmap IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }
}
