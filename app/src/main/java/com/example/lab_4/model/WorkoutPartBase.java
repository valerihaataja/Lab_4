package com.example.lab_4.model;

import java.io.Serializable;

public abstract class WorkoutPartBase implements Serializable {

    abstract public String getName();
    abstract public void setName();
    abstract public String getTime();
    abstract public void setTime(String data);
}
