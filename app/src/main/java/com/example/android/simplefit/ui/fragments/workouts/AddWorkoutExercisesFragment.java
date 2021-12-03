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
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.data.adapters.ExerciseInWorkoutAdapter;
import com.example.android.simplefit.models.db.Workout;
import com.example.android.simplefit.viewmodels.ExerciseInWorkoutViewModel;
import com.example.android.simplefit.viewmodels.WorkoutViewModel;

public class AddWorkoutExercisesFragment extends Fragment {

    private RecyclerView exercisesRV;

    public AddWorkoutExercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_workout_exercises, container, false);
        int workout_id = AddWorkoutExercisesFragmentArgs.fromBundle(getArguments()).getWorkoutId();
        WorkoutViewModel viewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        ExerciseInWorkoutViewModel viewModelExercise = new ViewModelProvider(this).get(ExerciseInWorkoutViewModel.class);

        exercisesRV = view.findViewById(R.id.workoutRecyclerView);

        exercisesRV.hasFixedSize();
        exercisesRV.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ExerciseInWorkoutAdapter adapter = new ExerciseInWorkoutAdapter(viewModelExercise);

        exercisesRV.setAdapter(adapter);
        viewModelExercise.getExercisesInWorkout(workout_id).observe(getViewLifecycleOwner(), adapter::setExercisesInWorkout);

        Button addExercise = view.findViewById(R.id.addExerciseWorkoutButton);
        addExercise.setOnClickListener(v -> {
            AddWorkoutExercisesFragmentDirections.ActionAddWorkoutFragmentToAddExerciseForWorkoutFragment action = AddWorkoutExercisesFragmentDirections.actionAddWorkoutFragmentToAddExerciseForWorkoutFragment(workout_id);
            Navigation.findNavController(view).navigate(action);
        });

        Button done = view.findViewById(R.id.doneWorkoutButton);
        done.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_addWorkoutFragment_to_workoutListFragment));

        return view;
    }
}