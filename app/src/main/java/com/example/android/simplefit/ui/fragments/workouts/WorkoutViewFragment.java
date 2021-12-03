package com.example.android.simplefit.ui.fragments.workouts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.simplefit.R;
import com.example.android.simplefit.data.adapters.ExerciseInWorkoutAdapter;
import com.example.android.simplefit.viewmodels.ExerciseInWorkoutViewModel;
import com.example.android.simplefit.viewmodels.WorkoutViewModel;

public class WorkoutViewFragment extends Fragment {

    public WorkoutViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_view, container, false);
        int workout_id = AddExerciseForWorkoutFragmentArgs.fromBundle(getArguments()).getWorkoutId();
        WorkoutViewModel viewModelWorkout = new ViewModelProvider(this).get(WorkoutViewModel.class);
        ExerciseInWorkoutViewModel viewModelExercise = new ViewModelProvider(this).get(ExerciseInWorkoutViewModel.class);

        RecyclerView exercisesRV = view.findViewById(R.id.exercisesInWorkoutRecyclerView);
        exercisesRV.hasFixedSize();
        exercisesRV.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ExerciseInWorkoutAdapter adapter = new ExerciseInWorkoutAdapter(viewModelExercise);
        exercisesRV.setAdapter(adapter);
        viewModelExercise.getExercisesInWorkout(workout_id).observe(getViewLifecycleOwner(), adapter::setExercisesInWorkout);

        Button back = view.findViewById(R.id.backToWorkoutListButton);
        back.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_workoutViewFragment_to_workoutListFragment));

        Button delete = view.findViewById(R.id.deleteWorkoutButton);
        delete.setOnClickListener(v -> {
            viewModelWorkout.deleteWorkoutById(workout_id);

            Navigation.findNavController(view).navigate(R.id.action_workoutViewFragment_to_workoutListFragment);
        });

        return view;
    }
}