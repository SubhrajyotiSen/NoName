package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

public class DashboardModel {

    private String amount;
    private String status;
    private float change;
    @SerializedName("desc")
    private String description;
    private String when;


    public DashboardModel(String amount, String status, float change, String description, String when) {
        this.amount = amount;
        this.status = status;
        this.change = change;
        this.description = description;
        this.when = when;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public float getChange() {
        return change;
    }

    public String getDescription() {
        return description;
    }

    public String getWhen() {
        return when;
    }
}
