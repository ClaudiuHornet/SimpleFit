package com.example.android.simplefit.models.db;

import android.accounts.Account;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "exerciseInWorkout", foreignKeys = {
        @ForeignKey(entity = Workout.class, parentColumns = "workout_id", childColumns = "workout_id")})
public class ExerciseInWorkout {

    @PrimaryKey(autoGenerate = true)
    private int exerciseInWorkout_id;

    private int workout_id;

    private String name;
    private int sets;
    private int reps;

    public ExerciseInWorkout(int workout_id, String name, int sets, int reps) {
        this.workout_id = workout_id;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }

    @Ignore
    public ExerciseInWorkout(int exerciseInWorkout_id, int workout_id, String name, int sets, int reps) {
        this.exerciseInWorkout_id = exerciseInWorkout_id;
        this.workout_id = workout_id;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }

    public int getExerciseInWorkout_id() {
        return exerciseInWorkout_id;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }

    public int getExercisesWorkout_id() {
        return exerciseInWorkout_id;
    }

    public void setExerciseInWorkout_id(int exerciseInWorkout_id) {
        this.exerciseInWorkout_id = exerciseInWorkout_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "exerciseInWorkout_id=" + exerciseInWorkout_id +
                ", name='" + name + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                '}';
    }
}
