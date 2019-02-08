package com.example.lab_4.model;

public class PausePart extends WorkoutPartBase {

    public String name;
    public String time;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName() {
        this.name = "Pause";
    }

    @Override
    public String getTime() {
        return time;}

    @Override
    public void setTime(String data) {
        time = data;
    }
}
