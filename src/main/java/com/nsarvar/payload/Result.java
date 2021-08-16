package com.nsarvar.payload;

import com.nsarvar.decathlon.Decathlon;

public class Result {
    private Decathlon.Event event;
    private String score;
    private long points;

    public Result(Decathlon.Event event, String score) {
        this.score = score;
        this.event = event;
        this.points = event.calculateResult(score);
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Decathlon.Event getEvent() {
        return event;
    }

    public void setEvent(Decathlon.Event event) {
        this.event = event;
    }

    public long getPoints() {
        return points;
    }
}
