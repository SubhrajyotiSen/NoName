package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class BirthdayModel {

    private String name;
    private String role;
    @SerializedName("image_url")
    private String imageURL;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getImageURL() {
        return imageURL;
    }
}
