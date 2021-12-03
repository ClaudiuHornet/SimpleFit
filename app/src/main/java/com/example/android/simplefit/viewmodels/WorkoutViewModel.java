package com.example.android.simplefit.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.simplefit.data.repositories.WorkoutRepository;
import com.example.android.simplefit.models.db.ExerciseInWorkout;
import com.example.android.simplefit.models.db.Workout;
import com.example.android.simplefit.models.db.WorkoutWithExercise;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {

    private WorkoutRepository workoutRepository;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutRepository = WorkoutRepository.getInstance(application);
    }

    public void addWorkout(Workout workout){
        workoutRepository.addWorkout(workout);
    }

    public LiveData<List<Workout>> getWorkouts(){
        return workoutRepository.getWorkouts();
    }

    public void updateWorkout(Workout workout){
        workoutRepository.updateWorkout(workout);
    }

    public void deleteWorkoutById(int workout_id){
        workoutRepository.deleteWorkoutById(workout_id);
    }

    public LiveData<List<WorkoutWithExercise>> getAllWorkoutsWithExercises(){
        return workoutRepository.getAllWorkoutsWithExercises();
    }

    public LiveData<Workout> getWorkout(String name)
    {
        return workoutRepository.getWorkout(name);
    }
}
