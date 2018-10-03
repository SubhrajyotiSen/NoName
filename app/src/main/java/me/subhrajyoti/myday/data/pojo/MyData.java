package me.subhrajyoti.myday.data.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class MyData {

    private String type;
    private String scroll;
    private JsonArray data;
    private List<Object> dataList;

    public MyData() {
        dataList = new ArrayList<>();
    }

    public MyData(String type, String scroll, List<Object> dataList) {
        this.type = type;
        this.scroll = scroll;
        this.dataList = dataList;
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

    public List<Object> getDataList() {
        return dataList;
    }

    public <T> void makeArrayListFromJsonArray(Class<T> tClass) {
        Gson gson = new Gson();
        for (int i = 0; i < data.size(); i++) {
            JsonElement jsonElement = data.get(i);
            dataList.add(gson.fromJson(jsonElement, tClass));
        }
    }

}
