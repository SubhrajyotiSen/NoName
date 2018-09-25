package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class QuickViewModel {

    @SerializedName("header")
    private String cardHeader;
    @SerializedName("subtext")
    private String subText;

    public QuickViewModel(String cardHeader, String subText) {
        this.cardHeader = cardHeader;
        this.subText = subText;
    }

    public String getCardHeader() {
        return cardHeader;
    }

    public String getSubText() {
        return subText;
    }
}
