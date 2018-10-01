package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class NewMemberModel {

    @SerializedName("image_url")
    private String imageURL;
    private String name;
    private String role;

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
