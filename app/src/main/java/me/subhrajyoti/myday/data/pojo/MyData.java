package me.subhrajyoti.myday.data.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class MyData {

    public String type;
    public JsonArray data;
    public List<Object> dataList;
    public String header;
    public List<NewMemberModel>memberModelList = new ArrayList<>();
    public List<EventModel>eventModelList = new ArrayList<>();
    public List<ChannelUpdateModel>channelUpdateModelList = new ArrayList<>();
    public List<ChannelModel>channelModelList = new ArrayList<>();
    public List<ProjectModel>projectModelList = new ArrayList<>();
    public List<QuickViewModel>quickModelList = new ArrayList<>();
    public List<TeamUpdateModel>teamUpdateModelList = new ArrayList<>();
    public List<DashboardModel>dashBoardModelList = new ArrayList<>();
    public List<PollModel>pollModelList = new ArrayList<>();
    public List<EmployeeUpdateModel>employeeUpdateModel = new ArrayList<>();


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

        List dl = getList(tClass);
        for (int i = 0; i < data.size(); i++) {
            JsonElement jsonElement = data.get(i);
            dl.add(gson.fromJson(jsonElement, tClass));
        }
    }

    public <T> void makeArrayListFromJsonArray(Class<T> tClass,JsonArray data) {
        Gson gson = new Gson();

        List dl = getList(tClass);
        for (int i = 0; i < data.size(); i++) {
            JsonElement jsonElement = data.get(i);
            dl.add(gson.fromJson(jsonElement, tClass));
        }
    }


    public List getList(Object c)
    {

        if (c.equals( NewMemberModel.class)){
            return memberModelList;
        }
        else if (c.equals(EventModel.class)){
            return eventModelList;
        }
        else if (c.equals(ChannelModel.class)){
            return channelModelList;
        }
        else if (c.equals(ProjectModel.class)){
            return projectModelList;
        }
        else if (c.equals(QuickViewModel.class)){
            return quickModelList;
        }
        else if (c.equals(TeamUpdateModel.class)){
            return teamUpdateModelList;
        }
        else if (c.equals(DashboardModel.class)){
            return dashBoardModelList;

        }else if (c.equals(PollModel.class)){
            return pollModelList;
        }
        else if (c.equals(EmployeeUpdateModel.class)){
            return employeeUpdateModel;
        }else if (c.equals(ChannelUpdateModel.class)){
            return channelUpdateModelList;
        }

        return dataList;

    }

}
