package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class ClaimModel {

    private String name;
    @SerializedName("value")
    private String amount;
    private int bills;
    @SerializedName("image_url")
    private String imageURL;

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public int getBills() {
        return bills;
    }

    public String getImageURL() {
        return imageURL;
    }
}
