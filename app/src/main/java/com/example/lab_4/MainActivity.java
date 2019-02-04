package com.example.lab_4;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.example.lab_4.model.PausePart;
import com.example.lab_4.model.WorkoutPart;
import com.example.lab_4.model.WorkoutPartBase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int NEW_PART_REQ_ID = 311;
    ArrayList<WorkoutPartBase> workouts = new ArrayList<>();
    ListView list_view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = findViewById(R.id.list_view);
        findViewById(R.id.button_start_workout).setOnClickListener(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent = new Intent(this, AddNewPartActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        WorkoutArrayAdapter adapter = new WorkoutArrayAdapter(this, workouts);
        list_view.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_add) {
            Intent intent = new Intent(this, AddNewPartActivity.class);
            startActivityForResult(intent, NEW_PART_REQ_ID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_PART_REQ_ID && requestCode == RESULT_OK){
            WorkoutPartBase workoutPartBase = (WorkoutPartBase) data.getSerializableExtra("WORKOUTPART");
            workouts.add(workoutPartBase);
        }
    }
}
