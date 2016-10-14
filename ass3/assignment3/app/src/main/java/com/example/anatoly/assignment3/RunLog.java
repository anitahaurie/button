package com.example.anatoly.assignment3;

/**
 * Created by anatoly on 10/10/16.
 */

public class RunLog {
    private int id;
    private float distance;     // in km
    private float duration;     // in minutes
    private float avg_speed;    // in km/h
    private int calories;       // unitless

    public RunLog() {}

    public RunLog(int id, float dist, float dur) {
        this.setID(id);
        this.setDistance(dist);
        this.setDuration(dur);
        this.setAvgSpeed(/*dist, dur*/);
        this.setCalories();
    }

    public void setID(int id) {       //TODO: FIX THIS
        this.id = id;
    }

    public void setDistance(float dist) {
        this.distance = dist;
    }

    public void setDuration(float dur) {
        this.duration = dur;
    }

    public void setAvgSpeed( /*float dist, float dur*/ ) {
        float dist = this.getDistance();
        float dur = this.getDuration();

        this.avg_speed = dist / (dur / 60);
    }

    public void setCalories() {     // TODO: compute calories burnt
        float dist = this.getDistance();
        float dur = this.getDuration();

        this.calories = (int) (dist + dur);
    }

    public int getID() {
        return this.id;
    }

    public float getDistance() {
        return this.distance;
    }

    public float getDuration() {
        return this.duration;
    }

    public float getAvgSpeed() {
        return this.avg_speed;
    }

    public int getCalories() {
        return this.calories;
    }
}
