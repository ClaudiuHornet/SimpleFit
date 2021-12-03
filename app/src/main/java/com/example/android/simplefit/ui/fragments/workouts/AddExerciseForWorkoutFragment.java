package com.example.android.simplefit.ui.fragments.workouts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.models.db.ExerciseInWorkout;
import com.example.android.simplefit.viewmodels.ExerciseInWorkoutViewModel;
import com.example.android.simplefit.viewmodels.WorkoutViewModel;

public class AddExerciseForWorkoutFragment extends Fragment {

    private EditText name;
    private EditText sets;
    private EditText reps;

    private TextView nameError;
    private TextView repsError;
    private TextView setsError;

    public AddExerciseForWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_exercise_for_workout, container, false);
        int workout_id = AddExerciseForWorkoutFragmentArgs.fromBundle(getArguments()).getWorkoutId();
        ExerciseInWorkoutViewModel viewModel = new ViewModelProvider(this).get(ExerciseInWorkoutViewModel.class);

        name = view.findViewById(R.id.addNameEditText);
        sets = view.findViewById(R.id.addSetsEditText);
        reps = view.findViewById(R.id.addRepsEditText);

        nameError = view.findViewById(R.id.addNameError);
        setsError = view.findViewById(R.id.addSetsError);
        repsError = view.findViewById(R.id.addRepsError);

        nameError.setVisibility(View.GONE);
        setsError.setVisibility(View.GONE);
        repsError.setVisibility(View.GONE);

        Button button = view.findViewById(R.id.addExerciseToWorkoutButton);

        button.setOnClickListener(v -> {
            if(!isValid()){
                return;
            }
            ExerciseInWorkout exercise = new ExerciseInWorkout(workout_id, name.getText().toString(), Integer.parseInt(sets.getText().toString()), Integer.parseInt(reps.getText().toString()));
            viewModel.addExerciseInWorkout(exercise);
            AddExerciseForWorkoutFragmentDirections.ActionAddExerciseForWorkoutFragmentToAddWorkoutFragment action = AddExerciseForWorkoutFragmentDirections.actionAddExerciseForWorkoutFragmentToAddWorkoutFragment(workout_id);
            Navigation.findNavController(view).navigate(action);
        });

        return view;
    }

    private boolean isValid(){
        boolean isValid = true;

        if(name.getText().toString().equals("")){
            nameError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            nameError.setVisibility(View.GONE);
        }

        if(sets.getText().toString().equals("")){
            setsError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            setsError.setVisibility(View.GONE);
        }

        if(reps.getText().toString().equals("")){
            repsError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            repsError.setVisibility(View.GONE);
        }

        return isValid;
    }
}