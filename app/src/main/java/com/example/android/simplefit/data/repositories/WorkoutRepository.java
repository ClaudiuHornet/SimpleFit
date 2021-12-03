package com.example.android.simplefit.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.simplefit.data.dao.ExerciseInWorkoutDAO;
import com.example.android.simplefit.data.dao.SimpleFitDatabase;
import com.example.android.simplefit.data.dao.WorkoutDAO;
import com.example.android.simplefit.models.db.ExerciseInWorkout;
import com.example.android.simplefit.models.db.Workout;
import com.example.android.simplefit.models.db.WorkoutWithExercise;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutRepository {
    private static WorkoutRepository instance;
    private WorkoutDAO workoutDAO;
    private ExecutorService executorService;
    private LiveData<List<Workout>> allWorkouts;
    private LiveData<List<WorkoutWithExercise>> allWorkoutsWithExercises;

    private WorkoutRepository(Application application)
    {
        workoutDAO = SimpleFitDatabase.getInstance(application).workoutDAO();
        allWorkouts = workoutDAO.getAllWorkouts();
        allWorkoutsWithExercises = workoutDAO.getAllWorkoutsWithExercise();
        executorService = Executors.newFixedThreadPool(2);
    }

    public LiveData<Workout> getWorkout(String name)
    {
        return workoutDAO.getWorkout(name);
    }

    public static WorkoutRepository getInstance(Application application)
    {
        if(instance == null) {
            instance = new WorkoutRepository(application);
        }
        return instance;
    }

    public LiveData<List<WorkoutWithExercise>> getAllWorkoutsWithExercises(){
        return allWorkoutsWithExercises;
    }

    public void addWorkout(Workout workout)
    {
        executorService.execute(() -> workoutDAO.insert(workout));
    }

    public LiveData<List<Workout>> getWorkouts(){
        allWorkouts = workoutDAO.getAllWorkouts();
        return allWorkouts;
    }



    public void updateWorkout(Workout workout)
    {
        executorService.execute(() -> workoutDAO.update(workout));
    }

    public void deleteWorkoutById(int workout_id)
    {
        executorService.execute(() -> workoutDAO.deleteById(workout_id));
    }
}
