package com.example.android.simplefit.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.simplefit.models.db.Workout;
import com.example.android.simplefit.models.db.WorkoutWithExercise;

import java.util.List;

@Dao
public interface WorkoutDAO {
    @Insert
    void insert(Workout workout);

    @Update
    void update(Workout workout);

    @Delete
    void delete(Workout workout);

    @Query("SELECT * FROM workouts ORDER BY workout_id DESC")
    LiveData<List<Workout>> getAllWorkouts();

    @Query("SELECT * FROM workouts ORDER BY workout_id DESC")
    LiveData<List<WorkoutWithExercise>> getAllWorkoutsWithExercise();

    @Query("SELECT * FROM workouts WHERE name = :name")
    LiveData<Workout> getWorkout(String name);

    @Query("DELETE FROM workouts WHERE workout_id = :workout_id")
    void deleteById(int workout_id);
}
