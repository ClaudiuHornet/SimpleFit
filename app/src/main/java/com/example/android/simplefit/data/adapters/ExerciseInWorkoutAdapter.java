package com.example.android.simplefit.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.models.db.ExerciseInWorkout;
import com.example.android.simplefit.viewmodels.ExerciseInWorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExerciseInWorkoutAdapter extends RecyclerView.Adapter<ExerciseInWorkoutAdapter.ExerciseInWorkoutViewHolder> {
    private List<ExerciseInWorkout> exercises = new ArrayList<>();
    private ExerciseInWorkoutViewModel viewModel;

    public ExerciseInWorkoutAdapter(ExerciseInWorkoutViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ExerciseInWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ExerciseInWorkoutViewHolder(inflater.inflate(R.layout.workout_exercise_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseInWorkoutViewHolder holder, int position) {
        ExerciseInWorkout exercise = exercises.get(position);

        holder.exerciseInformation.setText(exercise.getName() + ", reps: " + String.valueOf(exercise.getReps()) + ", sets: " + String.valueOf(exercise.getSets()) );
        holder.deleteButton.setOnClickListener(v -> {
            viewModel.deleteExerciseInWorkout(exercise);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercisesInWorkout(List<ExerciseInWorkout> exercises)
    {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public class ExerciseInWorkoutViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseInformation;
        public ImageButton deleteButton;

        public ExerciseInWorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseInformation = itemView.findViewById(R.id.exerciseItemInformation);
            deleteButton = itemView.findViewById(R.id.deleteExerciseInWorkoutButton);
        }
    }

}


