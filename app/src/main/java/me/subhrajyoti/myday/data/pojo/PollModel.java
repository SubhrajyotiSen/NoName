package me.subhrajyoti.myday.data.pojo;

public class PollModel {

    private String deadline;
    private String question;

    public PollModel(String deadline, String question) {
        this.deadline = deadline;
        this.question = question;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getQuestion() {
        return question;
    }
}
