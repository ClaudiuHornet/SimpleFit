package com.example.android.simplefit.data.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.simplefit.models.db.BodyData;
import com.example.android.simplefit.models.db.ExerciseInWorkout;
import com.example.android.simplefit.models.db.Workout;

@Database(entities = {BodyData.class, Workout.class, ExerciseInWorkout.class}, version = 2)
public abstract class SimpleFitDatabase extends RoomDatabase {

    private static SimpleFitDatabase instance;

    public abstract BodyDataDAO bodyDataDAO();
    public abstract WorkoutDAO workoutDAO();
    public abstract ExerciseInWorkoutDAO exerciseInWorkoutDAO();

    public static synchronized SimpleFitDatabase getInstance(Context context)
    {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SimpleFitDatabase.class, "simple_fit_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }
}
