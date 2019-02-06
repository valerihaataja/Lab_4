package com.example.lab_4.model;

public class WorkoutPart extends WorkoutPartBase {

    public String name;
    public String time;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName() {
        this.name = "Workout";
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setTime(String data) {
        time = data;
    }
}
