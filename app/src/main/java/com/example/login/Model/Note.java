package com.example.login.Model;

public class Note {
    private String title;
    private String desc;
    private String date;
    private String key;

    public Note() {
    }

    public Note( String key, String title, String desc, String date) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.key = key;
    }

    public String getKey() {
        return key;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}

