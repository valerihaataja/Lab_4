package com.example.lab_4;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;



import com.example.lab_4.model.WorkoutPartBase;

import java.util.ArrayList;
import java.util.ListIterator;

public class ActivityRunProgram extends AppCompatActivity {

    TextView textview_what_type;
    TextView textview_time;
    ArrayList<WorkoutPartBase> workouts;
    ListIterator<WorkoutPartBase> iterator;
    String currentWorkout;
    String aika;
    String workout_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_program);
        textview_what_type.findViewById(R.id.textview_what_type);
        textview_time.findViewById(R.id.textview_time);
        ArrayList<WorkoutPartBase> workouts = (ArrayList<WorkoutPartBase>) getIntent().getSerializableExtra("WORKOUTS");
        iterator = workouts.listIterator();

        iterator.hasNext();
    }





}
