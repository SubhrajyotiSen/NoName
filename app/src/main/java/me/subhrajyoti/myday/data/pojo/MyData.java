package me.subhrajyoti.myday.data.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class MyData {

    private String type;
    private JsonArray data;
    private List<Object> dataList;
    private String header;

    public MyData() {
        dataList = new ArrayList<>();
    }

    public MyData(String type, List<Object> dataList) {
        this.type = type;
        this.dataList = dataList;
    }

    public MyData(String type, String header) {
        this.header = header;
        this.type = type;
    }

    public MyData(String type, String header, List<Object> dataList) {
        this.header = header;
        this.type = type;
        this.dataList = dataList;
    }

    public String getType() {
        return type;
    }

    public JsonArray getData() {
        return data;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public String getHeader() {
        return header;
    }

    public <T> void makeArrayListFromJsonArray(Class<T> tClass) {
        Gson gson = new Gson();
        for (int i = 0; i < data.size(); i++) {
            JsonElement jsonElement = data.get(i);
            dataList.add(gson.fromJson(jsonElement, tClass));
        }
    }

}
