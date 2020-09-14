package com.example.myretrofit.Model;

import com.google.gson.annotations.SerializedName;

public class Post {
    int id;
    int userId;
    String title;
    @SerializedName("body")
    String bodyText; // use @SerializedName() if name different from the key

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }
}
