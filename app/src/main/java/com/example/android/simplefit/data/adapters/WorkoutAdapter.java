package com.example.android.simplefit.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.models.db.Workout;
import com.example.android.simplefit.viewmodels.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private List<Workout> workouts = new ArrayList<>();
    private WorkoutViewModel viewModel;
    private WorkoutAdapterOnClickListener listener;

    public WorkoutAdapter(WorkoutViewModel viewModel, WorkoutAdapterOnClickListener listener){
        this.viewModel = viewModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new WorkoutViewHolder(inflater.inflate(R.layout.workout_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutViewHolder holder, int position) {
        Workout currentWorkout = workouts.get(position);

        holder.workoutName.setText(currentWorkout.getName());
        holder.openWorkout.setOnClickListener(view -> listener.onWorkoutCardClick(currentWorkout));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(List<Workout> workouts){
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {

        public TextView workoutName;
        public Button openWorkout;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);

            workoutName = itemView.findViewById(R.id.workoutCardName);
            openWorkout = itemView.findViewById(R.id.openWorkoutButton);
        }
    }

    public interface WorkoutAdapterOnClickListener{
        void onWorkoutCardClick(Workout workout);
    }
}
