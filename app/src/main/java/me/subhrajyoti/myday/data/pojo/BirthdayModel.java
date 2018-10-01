package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class BirthdayModel {

    private String name;
    private String role;
    private String birthday;
    @SerializedName("image_url")
    private String imageURL;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getImageURL() {
        return imageURL;
    }
}
