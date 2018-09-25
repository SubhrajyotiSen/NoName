package me.subhrajyoti.myday.data.pojo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

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

    public ProjectModel(JsonObject jsonObject) {
        projectName = jsonObject.get("name").getAsString();
        tasksCompleted = jsonObject.get("tasks_completed").getAsInt();
        tasksTotal = jsonObject.get("tasks_total").getAsInt();
        deadline = jsonObject.get("deadline").getAsString();
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
