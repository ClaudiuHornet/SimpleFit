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
import com.example.android.simplefit.data.adapters.WorkoutAdapter;
import com.example.android.simplefit.viewmodels.WorkoutViewModel;

public class WorkoutListFragment extends Fragment {

    public WorkoutListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_list, container, false);
        WorkoutViewModel viewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        RecyclerView workoutRV = view.findViewById(R.id.workoutListRecyclerView);
        workoutRV.hasFixedSize();
        workoutRV.setLayoutManager(new LinearLayoutManager(view.getContext()));

        WorkoutAdapter adapter = new WorkoutAdapter(viewModel, workout -> {
            WorkoutListFragmentDirections.ActionWorkoutListFragmentToWorkoutViewFragment action = WorkoutListFragmentDirections.actionWorkoutListFragmentToWorkoutViewFragment(workout.getWorkout_id());
            Navigation.findNavController(view).navigate(action);
        });

        workoutRV.setAdapter(adapter);
        viewModel.getWorkouts().observe(getViewLifecycleOwner(), adapter::setWorkouts);

        Button button = view.findViewById(R.id.addWorkoutButton);
        button.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_workoutListFragment_to_createWorkoutNameFragment));

        return view;
    }
}