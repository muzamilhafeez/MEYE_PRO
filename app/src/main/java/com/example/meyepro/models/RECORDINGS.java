package com.example.meyepro.models;

import android.text.format.DateFormat;

public class RECORDINGS {
   int  ID ;
   int TH_ID ;
   String FILENAME ;
  DateFormat DATE ;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTH_ID() {
        return TH_ID;
    }

    public void setTH_ID(int TH_ID) {
        this.TH_ID = TH_ID;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public DateFormat getDATE() {
        return DATE;
    }

    public void setDATE(DateFormat DATE) {
        this.DATE = DATE;
    }
}
