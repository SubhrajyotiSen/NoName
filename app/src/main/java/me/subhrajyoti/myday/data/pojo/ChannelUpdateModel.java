package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class ChannelUpdateModel {

    @SerializedName("channel_name")
    private String channelName;
    private String update;
    private int likes;
    private int comments;

    public String getChannelName() {
        return channelName;
    }

    public String getUpdate() {
        return update;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }
}
