package com.example.android.simplefit.ui.fragments.bodyData;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.simplefit.R;
import com.example.android.simplefit.data.adapters.BodyDataAdapter;
import com.example.android.simplefit.models.db.BodyData;
import com.example.android.simplefit.viewmodels.BodyDataViewModel;

import java.util.List;

public class BodyDataListFragment extends Fragment {

    public BodyDataListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_data_list, container, false);
        BodyDataViewModel viewModel = new ViewModelProvider(this).get(BodyDataViewModel.class);

        RecyclerView bodyDataRV = view.findViewById(R.id.bodyDataRecyclerView);
        bodyDataRV.hasFixedSize();
        bodyDataRV.setLayoutManager(new LinearLayoutManager(view.getContext()));

        BodyDataAdapter adapter = new BodyDataAdapter(bodyData -> {
            BodyDataListFragmentDirections.ActionBodyDataListFragmentToBodyDataViewFragment action = BodyDataListFragmentDirections.actionBodyDataListFragmentToBodyDataViewFragment(bodyData);
            Navigation.findNavController(view).navigate(action);
        });


        bodyDataRV.setAdapter(adapter);
//        viewModel.getAllBodyData().observe(getViewLifecycleOwner(), adapter::setBodyDataList);
        viewModel.getAllBodyData().observe(getViewLifecycleOwner(), new Observer<List<BodyData>>() {
            @Override
            public void onChanged(List<BodyData> bodyData) {
                adapter.setBodyDataList(bodyData);
            }
        });

        Button button = view.findViewById(R.id.addMeasurementButton);
        button.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_bodyDataListFragment_to_addBodyDataFragment));

        return view;
    }
}