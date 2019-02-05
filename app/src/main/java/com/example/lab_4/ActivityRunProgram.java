package com.example.lab_4;

import android.os.Bundle;
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
    WorkoutPartBase currentType;
    String workout_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_program);
        textview_what_type.findViewById(R.id.textview_what_type);
        textview_time.findViewById(R.id.textview_time);
        workouts = (ArrayList<WorkoutPartBase>) getIntent().getSerializableExtra("WORKOUTS");
        iterator = workouts.listIterator();

    }

    private void iterate(){
        if(iterator.hasNext()){
            currentType = iterator.next();
            workout_type = currentType.getName();
            textview_what_type.setText(workout_type);
        }
        else{
            finish();
        }
    }


}
