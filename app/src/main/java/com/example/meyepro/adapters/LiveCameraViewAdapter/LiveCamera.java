package com.example.meyepro.adapters.LiveCameraViewAdapter;

public class LiveCamera {
    private String name;
    private String url;
    public LiveCamera(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}