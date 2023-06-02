package com.example.meyepro.models;

public class DVR {
    private int id;
    private String ip;
    private String name;
    private String channel;
    private String host;
    private String password;

    public DVR(int id, String ip, String name, String channel, String host, String password) {
        this.id = id;
        this.ip = ip;
        this.name = name;
        this.channel = channel;
        this.host = host;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
