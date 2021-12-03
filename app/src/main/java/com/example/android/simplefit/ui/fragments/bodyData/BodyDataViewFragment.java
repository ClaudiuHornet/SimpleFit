package com.example.android.simplefit.ui.fragments.bodyData;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.models.db.BodyData;
import com.example.android.simplefit.util.DateStringConverter;
import com.example.android.simplefit.viewmodels.BodyDataViewModel;


public class BodyDataViewFragment extends Fragment {


    public BodyDataViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_data_view, container, false);
        BodyData bodyData = BodyDataViewFragmentArgs.fromBundle(getArguments()).getBodyData();
        BodyDataViewModel viewModel = new ViewModelProvider(this).get(BodyDataViewModel.class);

        TextView weight = view.findViewById(R.id.bodyDataWeightEditText);
        TextView armMeasurement = view.findViewById(R.id.bodyDataArmEditText);
        TextView chestMeasurement = view.findViewById(R.id.bodyDataChestEditText);
        TextView waistMeasurement = view.findViewById(R.id.bodyDataWaistEditText);
        TextView legMeasurement = view.findViewById(R.id.bodyDataLegEditText);
        TextView date = view.findViewById(R.id.bodyDataDateEditText);

        weight.setText(String.valueOf(bodyData.getWeight()));
        armMeasurement.setText(String.valueOf(bodyData.getArmMeasurement()));
        chestMeasurement.setText(String.valueOf(bodyData.getChestMeasurement()));
        waistMeasurement.setText(String.valueOf(bodyData.getWaistMeasurement()));
        legMeasurement.setText(String.valueOf(bodyData.getLegMeasurement()));
        String[] dateSplit = bodyData.getDate().split(" ");
        date.setText(DateStringConverter.convertDbDateToUiDate(dateSplit[0]) + " " + dateSplit[1]);

        ImageButton deleteButton = view.findViewById(R.id.deleteBodyDataButton);
        deleteButton.setOnClickListener(v -> {
            viewModel.deleteBodyData(bodyData);

            Navigation.findNavController(view).navigate(R.id.action_bodyDataViewFragment_to_bodyDataListFragment);
        });

        return view;
    }
}