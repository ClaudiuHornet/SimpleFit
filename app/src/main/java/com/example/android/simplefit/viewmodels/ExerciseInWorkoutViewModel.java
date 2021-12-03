package com.example.android.simplefit.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.simplefit.data.repositories.ExerciseInWorkoutRepository;
import com.example.android.simplefit.models.db.ExerciseInWorkout;

import java.util.ArrayList;
import java.util.List;

public class ExerciseInWorkoutViewModel extends AndroidViewModel {
    private ExerciseInWorkoutRepository exerciseInWorkoutRepository;

    public ExerciseInWorkoutViewModel(@NonNull Application application) {
        super(application);
        exerciseInWorkoutRepository = ExerciseInWorkoutRepository.getInstance(application);
    }

    public LiveData<List<ExerciseInWorkout>> getExercisesInWorkout(int workout_id)
    {
        return exerciseInWorkoutRepository.getExercisesInOneWorkout(workout_id);
    }

    public void addExerciseInWorkout(ExerciseInWorkout exerciseInWorkout){
        exerciseInWorkoutRepository.addExerciseInWorkout(exerciseInWorkout);
    }

    public void deleteExerciseInWorkout(ExerciseInWorkout exerciseInWorkout){
        exerciseInWorkoutRepository.deleteExerciseInWorkout(exerciseInWorkout);
    }
}
