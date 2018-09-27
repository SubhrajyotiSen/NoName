package me.subhrajyoti.myday.data.pojo;

public class EventModel {

    private String date;
    private String name;
    private String venue;

    public EventModel(String date, String name, String venue) {
        this.date = date;
        this.name = name;
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }
}
