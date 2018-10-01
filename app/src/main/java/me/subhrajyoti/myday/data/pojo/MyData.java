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


    public String getType() {
        return type;
    }

    public JsonArray getData() {
        return data;
    }

    public String getScroll() {
        return scroll;
    }

    public  <T> ArrayList<T> makeArrayListFromJsonArray(Class<T> tClass) {
        ArrayList<T> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < data.size(); i++) {
            JsonElement jsonElement = data.get(i);
            arrayList.add(gson.fromJson(jsonElement, tClass));
        }
        return arrayList;
    }

}
