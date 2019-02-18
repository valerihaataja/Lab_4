package com.example.lab_4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Node;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int NEW_PART_REQ_ID = 311;
    ArrayList<WorkoutPartBase> workouts = null;
    ListView list_view = null;
    FileOutputStream outputstream;
    FileInputStream inputstream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = findViewById(R.id.list_view);
        findViewById(R.id.button_start_workout).setOnClickListener(this);

       workouts = new ArrayList<>();
       saveData();



    }

    @Override
    protected void onStop() {

        loadData();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)  {
            super.onOptionsItemSelected(item);
            int BtnID = item.getItemId();
            if(BtnID == R.id.button_new) {
                Intent intent = new Intent(this, AddNewPartActivity.class);
                startActivityForResult(intent, NEW_PART_REQ_ID);
            }
            if(BtnID == R.id.button_clear){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Clear workouts");
                builder.setMessage("Do you really want to clear workouts?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        workouts.clear();
                        onResume();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                });
                        AlertDialog alert = builder.create();
                         alert.show();

            }
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
        if(v.getId() == R.id.button_start_workout) {
            Intent intentStartWorkout = new Intent(this, ActivityRunProgram.class);
            intentStartWorkout.putExtra("WORKOUTS", workouts);
            startActivity(intentStartWorkout);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == NEW_PART_REQ_ID && resultCode == RESULT_OK){
            WorkoutPartBase workoutPartBase = (WorkoutPartBase) data.getSerializableExtra("WORKOUTPART");
            workouts.add(workoutPartBase);

        }
    }

    public void saveData(){
        try {
            inputstream = openFileInput("hiitFile");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputstream);
            workouts = (ArrayList)objectInputStream.readObject();
            objectInputStream.close();

            inputstream.close();

        }catch (IOException ioe){
            ioe.printStackTrace();
            return;
        }catch (ClassNotFoundException c){
            c.printStackTrace();
            return;
        }
    }
    public void loadData(){
        try {
            outputstream = openFileOutput("hiitFile", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputstream);
            objectOutputStream.writeObject(workouts);
            objectOutputStream.close();
            outputstream.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
