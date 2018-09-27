package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class ChannelUpdateModel {

    @SerializedName("channel_name")
    private String channelName;
    private String update;
    private int likes;
    private int comments;

    public ChannelUpdateModel(String channelName, String update, int likes, int comments) {
        this.channelName = channelName;
        this.update = update;
        this.likes = likes;
        this.comments = comments;
    }

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
