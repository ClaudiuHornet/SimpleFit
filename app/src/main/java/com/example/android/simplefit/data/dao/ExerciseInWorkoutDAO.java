package com.example.android.simplefit.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.simplefit.models.db.ExerciseInWorkout;
import com.example.android.simplefit.models.db.Workout;

import java.util.List;

@Dao
public interface ExerciseInWorkoutDAO {
    @Insert
    void insert(ExerciseInWorkout exerciseInWorkout);

    @Update
    void update(ExerciseInWorkout exerciseInWorkout);

    @Delete
    void delete(ExerciseInWorkout exerciseInWorkout);

    @Query("SELECT * FROM exerciseInWorkout ORDER BY exerciseInWorkout_id ASC")
    LiveData<List<ExerciseInWorkout>> getAllExercises();

    @Query("SELECT * FROM exerciseInWorkout WHERE workout_id = :workout_id")
    LiveData<List<ExerciseInWorkout>> getExercisesForWorkout(int workout_id);
}
