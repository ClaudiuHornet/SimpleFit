package com.example.android.simplefit.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.simplefit.data.dao.ExerciseInWorkoutDAO;
import com.example.android.simplefit.data.dao.SimpleFitDatabase;
import com.example.android.simplefit.models.db.ExerciseInWorkout;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExerciseInWorkoutRepository {
    private static ExerciseInWorkoutRepository instance;
    private ExerciseInWorkoutDAO exerciseInWorkoutDAO;
    private ExecutorService executorService;
    private LiveData<List<ExerciseInWorkout>> allExercises;

    private ExerciseInWorkoutRepository(Application application)
    {
        exerciseInWorkoutDAO = SimpleFitDatabase.getInstance(application).exerciseInWorkoutDAO();
        allExercises = exerciseInWorkoutDAO.getAllExercises();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static ExerciseInWorkoutRepository getInstance(Application application){
        if(instance == null)
        {
            instance = new ExerciseInWorkoutRepository(application);
        }

        return instance;
    }

    public LiveData<List<ExerciseInWorkout>> getExercisesInOneWorkout(int workout_id)
    {
        return exerciseInWorkoutDAO.getExercisesForWorkout(workout_id);
    }

    public void addExerciseInWorkout(ExerciseInWorkout exerciseInWorkout)
    {
        executorService.execute(() -> exerciseInWorkoutDAO.insert(exerciseInWorkout));
    }

    public LiveData<List<ExerciseInWorkout>> getAllExercises(){
        allExercises = exerciseInWorkoutDAO.getAllExercises();
        return allExercises;
    }

    public void deleteExerciseInWorkout(ExerciseInWorkout exerciseInWorkout){
        executorService.execute(() -> exerciseInWorkoutDAO.delete(exerciseInWorkout));
    }
}
