package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class ChannelModel {

    @SerializedName("name")
    private String ChannelName;
    @SerializedName("members")
    private int numberOfMembers;

    public String getChannelName() {
        return ChannelName;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }
}
