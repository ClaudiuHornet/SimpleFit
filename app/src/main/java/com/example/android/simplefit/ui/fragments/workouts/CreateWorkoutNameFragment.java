package com.example.android.simplefit.ui.fragments.workouts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.models.db.Workout;
import com.example.android.simplefit.viewmodels.WorkoutViewModel;

import java.util.List;
import java.util.Objects;

public class CreateWorkoutNameFragment extends Fragment {

    private EditText workoutName;
    private TextView nameError;

    public CreateWorkoutNameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_workout_name, container, false);
        WorkoutViewModel viewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        workoutName = view.findViewById(R.id.addWorkoutName);
        nameError = view.findViewById(R.id.addWorkoutNameError);

        nameError.setVisibility(View.GONE);

        Button button = view.findViewById(R.id.createWorkoutButton);
        button.setOnClickListener(v -> {
            if(!isValid()){
                return;
            }

            Workout workout = new Workout(workoutName.getText().toString());
            viewModel.addWorkout(workout);
            LiveData<Workout> createdWorkout = viewModel.getWorkout(workoutName.getText().toString());
            createdWorkout.observe(getViewLifecycleOwner(), new Observer<Workout>() {
                @Override
                public void onChanged(Workout workout) {
                    int workout_id = Objects.requireNonNull(createdWorkout.getValue()).getWorkout_id();
                    CreateWorkoutNameFragmentDirections.ActionCreateWorkoutNameFragmentToAddWorkoutFragment action = CreateWorkoutNameFragmentDirections.actionCreateWorkoutNameFragmentToAddWorkoutFragment(workout_id);
                    Navigation.findNavController(view).navigate(action);
                }
            });

        });

        return view;
    }

    private boolean isValid(){
        boolean isValid = true;

        if(workoutName.getText().toString().equals("")){
            nameError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            nameError.setVisibility(View.GONE);
        }

        return isValid;
    }
}