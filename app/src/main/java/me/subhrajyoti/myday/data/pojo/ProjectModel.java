package me.subhrajyoti.myday.data.pojo;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectModel {

    @SerializedName("name")
    private String projectName;
    @SerializedName("tasks_completed")
    private int tasksCompleted;
    @SerializedName("tasks_total")
    private int tasksTotal;
    private String deadline;

    public ProjectModel(String projectName, int tasksCompleted, int tasksTotal, String deadline) {
        this.projectName = projectName;
        this.tasksCompleted = tasksCompleted;
        this.tasksTotal = tasksTotal;
        this.deadline = deadline;
    }

    public ProjectModel(JSONObject jsonObject) throws JSONException {
        projectName = jsonObject.optString("name");
        tasksCompleted = jsonObject.optInt("tasks_completed");
        tasksTotal = jsonObject.optInt("tasks_total");
        deadline = jsonObject.optString("deadline");

    }


    public String getProjectName() {
        return projectName;
    }

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public int getTasksTotal() {
        return tasksTotal;
    }

    public String getDeadline() {
        return deadline;
    }
}
