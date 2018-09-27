package me.subhrajyoti.myday.data.pojo;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public class MyData {

    private String type;
    private String scroll;
    private JsonArray data;
    private ArrayList<BaseModel> models;

    public MyData(String type, String scroll, JsonArray data) {
        this.type = type;
        this.scroll = scroll;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public JsonArray getData() {
        return data;
    }

    public String getScroll() {
        return scroll;
    }

}
