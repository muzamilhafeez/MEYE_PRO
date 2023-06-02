package com.example.meyepro.models;

import android.graphics.Bitmap;

public class MEYE_USER {
    private int id;
    private String userID;
    private String name;
    private String image;
    private String password;
    private String role;


    public MEYE_USER(int id, String userID, String name, String image, String password, String role) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.image = image;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
