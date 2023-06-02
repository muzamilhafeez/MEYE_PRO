package com.example.meyepro.models;

public class CAMERA {
    private int id;
    private int dvrID;
    private int venueID;
    private String portNumber;
    private String venueName;

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public CAMERA(int id, int dvrID, int venueID, String portNumber, String venueName) {
        this.id = id;
        this.dvrID = dvrID;
        this.venueID = venueID;
        this.portNumber = portNumber;
        this.venueName = venueName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDvrID() {
        return dvrID;
    }

    public void setDvrID(int dvrID) {
        this.dvrID = dvrID;
    }

    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }
}
