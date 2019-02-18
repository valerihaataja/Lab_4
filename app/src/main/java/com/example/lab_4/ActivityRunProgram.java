package com.example.lab_4;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;



import com.example.lab_4.model.WorkoutPartBase;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Locale;

public class ActivityRunProgram extends AppCompatActivity {

    TextView timeTextview;
    TextView typeTextview;
    ListIterator<WorkoutPartBase> iterator;
    TextToSpeech textToSpeech;
    String name;
    WorkoutPartBase currentType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_program);
        ArrayList<WorkoutPartBase> workouts = (ArrayList<WorkoutPartBase>) getIntent().getSerializableExtra("WORKOUTS");
        typeTextview = (TextView)findViewById(R.id.textview_what_type);
        timeTextview = (TextView)findViewById(R.id.textview_time);
        iterator = workouts.listIterator();

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.US);
                    speakName();


                    iterate();

                }
            }
        });






    }

    private void iterate() {


        if (iterator.hasNext()) {


            currentType = iterator.next();
            name = currentType.getName();

            typeTextview.setText(name);
            speakName();


            startTimer();
            iterator.remove();
        }
        else {
            textToSpeech.shutdown();
            finish();
        }
    }



    private void startTimer() {


        // speakName();


        final String time = currentType.getTime();

        long duration = Integer.parseInt(time) * 1000;



        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {

                timeTextview.setText(String.valueOf(l / 1000));
                speakTime();

            }

            @Override
            public void onFinish() {
                iterate();
            }
        }.start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        textToSpeech.stop();
        textToSpeech.shutdown();
    }


    public void speakTime(){

        String currentTime = (String) timeTextview.getText();
        textToSpeech.speak(currentTime, TextToSpeech.QUEUE_FLUSH, null);

    }

    public void speakName(){


        try {
            textToSpeech.speak(name, TextToSpeech.QUEUE_FLUSH, null);
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}