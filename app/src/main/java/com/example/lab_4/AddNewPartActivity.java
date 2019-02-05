package com.example.lab_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lab_4.model.PausePart;
import com.example.lab_4.model.WorkoutPart;
import com.example.lab_4.model.WorkoutPartBase;

public class AddNewPartActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton radiobutton_workout;
    RadioButton radiobutton_pause;
    EditText text_editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_part);
        radiobutton_workout = findViewById(R.id.radiobutton_workout);
        radiobutton_pause =  findViewById(R.id.radiobutton_pause);
        text_editor = findViewById(R.id.text_editor);
        findViewById(R.id.button_add).setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        if(radiobutton_workout.isChecked()){
            WorkoutPart workoutPart = new WorkoutPart();
            workoutPart.setTime(text_editor.getText().toString());
            workoutPart.setName();
            returnData(workoutPart);
        }
        else if(radiobutton_pause.isChecked()){
            PausePart pausePart = new PausePart();
            pausePart.setTime(text_editor.getText().toString());
            pausePart.setName();
            returnData(pausePart);
        }
    }

    private void returnData(WorkoutPartBase data){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("WORKOUTPART",data);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
