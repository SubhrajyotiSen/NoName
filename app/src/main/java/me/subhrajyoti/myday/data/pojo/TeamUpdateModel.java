package me.subhrajyoti.myday.data.pojo;

public class TeamUpdateModel {

    private String update;
    private String topic;
    private String when;

    public TeamUpdateModel(String update, String topic, String when) {
        this.update = update;
        this.topic = topic;
        this.when = when;
    }

    public String getUpdate() {
        return update;
    }

    public String getTopic() {
        return topic;
    }

    public String getWhen() {
        return when;
    }
}
