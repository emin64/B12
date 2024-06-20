package com.example.myproje.model;

public class Post {
    public String email;
    public String comment;
    public String name;
    public String downloadUrl;

    public Post(String email, String comment, String name, String downloadUrl) {
        this.email = email;
        this.comment = comment;
        this.name = name;
        this.downloadUrl = downloadUrl;
    }

}
