package com.example.lab_4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab_4.model.PausePart;
import com.example.lab_4.model.WorkoutPart;
import com.example.lab_4.model.WorkoutPartBase;

import java.util.ArrayList;

public class WorkoutArrayAdapter extends ArrayAdapter<WorkoutPartBase> {

    static final int VIEW_TYPE_WORKOUT = 0;
    static final int VIEW_TYPE_PAUSE = 1;
    static final int VIEW_TYPE_COUNT = 2;

    public WorkoutArrayAdapter(Context context, ArrayList<WorkoutPartBase> workouts) {
        super(context, 0, workouts);
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        WorkoutPartBase workoutPartBase = getItem(position);

        if (workoutPartBase instanceof WorkoutPart) {
            return VIEW_TYPE_WORKOUT;
        } else {
            return VIEW_TYPE_PAUSE;
        }

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        WorkoutPartBase workoutPartBase = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            if (getItemViewType(position) == VIEW_TYPE_WORKOUT) {
                layoutId = R.layout.list_row_workout;
            } else {
                layoutId = R.layout.list_row_pause;
            }
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);

        }
        TextView workoutText = convertView.findViewById(R.id.time);
        workoutText.setText(workoutPartBase.getTime());
        return convertView;
    }
}



