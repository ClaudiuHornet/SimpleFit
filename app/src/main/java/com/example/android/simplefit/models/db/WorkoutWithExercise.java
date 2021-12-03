package com.example.android.simplefit.models.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithExercise {
    @Embedded public Workout workout;
    @Relation(
            parentColumn = "workout_id",
            entityColumn = "workout_id"
    )
    public List<ExerciseInWorkout> exercisesInOneWorkout;
}
