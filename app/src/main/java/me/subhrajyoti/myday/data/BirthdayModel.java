package me.subhrajyoti.myday.data;

import org.json.JSONException;
import org.json.JSONObject;

public class BirthdayModel {

    private String name;
    private String role;
    private String birthday;
    private String imageURL;

    public BirthdayModel(String name, String role, String birthday, String imageURL) {
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.imageURL = imageURL;
    }

    public BirthdayModel(JSONObject jsonObject) throws JSONException {
        name = jsonObject.optString("name");
        role = jsonObject.optString("role");
        birthday = jsonObject.optString("birthday");
        imageURL = jsonObject.optString("image_url");

    }


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
