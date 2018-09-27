package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class NewMemberModel {

    @SerializedName("image_url")
    private String imageURL;
    private String name;
    private String role;

    public NewMemberModel(String imageURL, String name, String role) {
        this.imageURL = imageURL;
        this.name = name;
        this.role = role;
    }

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
