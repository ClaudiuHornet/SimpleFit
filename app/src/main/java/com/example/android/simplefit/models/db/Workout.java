package com.example.android.simplefit.models.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "workouts")
public class Workout implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int workout_id;
    private String name;

    public Workout(String name) {
        this.name = name;
    }

    @Ignore
    public Workout(int workout_id, String name) {
        this.workout_id = workout_id;
        this.name = name;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
