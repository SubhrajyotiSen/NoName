package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class EmployeeUpdateModel {

    @SerializedName("image_url")
    private String imageURL;
    private String name;
    private String role;
    private String time;
    private String text;
    private int likes;
    private int comments;

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }
}
