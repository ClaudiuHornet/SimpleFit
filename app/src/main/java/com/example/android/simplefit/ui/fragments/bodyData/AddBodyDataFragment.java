package com.example.android.simplefit.ui.fragments.bodyData;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.models.db.BodyData;
import com.example.android.simplefit.viewmodels.BodyDataViewModel;

import java.util.Calendar;

public class AddBodyDataFragment extends Fragment {

    private EditText weight;
    private EditText armMeasurement;
    private EditText chestMeasurement;
    private EditText waistMeasurement;
    private EditText legMeasurement;
    private EditText date;

    private TextView weightError;
    private TextView armError;
    private TextView chestError;
    private TextView waistError;
    private TextView legError;
    private TextView dateError;

    public AddBodyDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_body_data, container, false);
        BodyDataViewModel viewModel = new ViewModelProvider(this).get(BodyDataViewModel.class);

        weight = view.findViewById(R.id.addWeight);
        armMeasurement = view.findViewById(R.id.addArmMeasurement);
        chestMeasurement = view.findViewById(R.id.addChestMeasurement);
        waistMeasurement = view.findViewById(R.id.addWaistMeasurement);
        legMeasurement = view.findViewById(R.id.addLegMeasurement);
        date = view.findViewById(R.id.addDate);

        weightError = view.findViewById(R.id.addWeightError);
        armError = view.findViewById(R.id.addArmMeasurementError);
        chestError = view.findViewById(R.id.addChestMeasurementError);
        waistError = view.findViewById(R.id.addWaistMeasurementError);
        legError = view.findViewById(R.id.addLegMeasurementError);
        dateError = view.findViewById(R.id.addDateError);

        weightError.setVisibility(View.GONE);
        armError.setVisibility(View.GONE);
        chestError.setVisibility(View.GONE);
        waistError.setVisibility(View.GONE);
        legError.setVisibility(View.GONE);
        dateError.setVisibility(View.GONE);

        Button button = view.findViewById(R.id.finishButton);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHour = calendar.get(Calendar.HOUR);
        int currentMinute = calendar.get(Calendar.MINUTE);

        final String[] dateString = {""};
        date.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(view.getContext(), (datePicker, year, month, day) -> {
                month += 1;
                dateString[0] = year + "-" + convertDayMonthToString(month) + "-" + convertDayMonthToString(day);
                date.setText(convertDayMonthToString(day) + "/" + convertDayMonthToString(month) + "/" + year);

                TimePickerDialog timeDialog = new TimePickerDialog(view.getContext(), (timePicker, hour, minute) -> {
                    dateString[0] += " "  + convertDayMonthToString(hour) + ":" + convertDayMonthToString(minute);
                    date.setText(date.getText() + " "  + convertDayMonthToString(hour) + ":" + convertDayMonthToString(minute));
                }, currentHour, currentMinute, true);
                timeDialog.show();
            }, currentYear, currentMonth, currentDay);
            dialog.show();
        });

        button.setOnClickListener(v -> {
            boolean isValid = validateForm();
            if (!isValid) {
                return;
            }

            BodyData bodyData = new BodyData(Float.parseFloat(weight.getText().toString()), Integer.parseInt(armMeasurement.getText().toString()),
                    Integer.parseInt(chestMeasurement.getText().toString()), Integer.parseInt(waistMeasurement.getText().toString()),
                    Integer.parseInt(legMeasurement.getText().toString()), dateString[0]);

            viewModel.addBodyData(bodyData);

            Log.i("bodyData", bodyData.toString());

            Navigation.findNavController(view).navigate(R.id.action_addBodyDataFragment_to_bodyDataListFragment);

        });

        return view;
    }


    private boolean validateForm() {
        boolean isValid = true;

        if(weight.getText().toString().equals(""))
        {
            weightError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            weightError.setVisibility(View.GONE);
        }

        if(armMeasurement.getText().toString().equals(""))
        {
            armError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            armError.setVisibility(View.GONE);
        }

        if(chestMeasurement.getText().toString().equals(""))
        {
            chestError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            chestError.setVisibility(View.GONE);
        }

        if(waistMeasurement.getText().toString().equals(""))
        {
            waistError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            waistError.setVisibility(View.GONE);
        }

        if(legMeasurement.getText().toString().equals(""))
        {
            legError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            legError.setVisibility(View.GONE);
        }

        if(date.getText().toString().equals(""))
        {
            dateError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            dateError.setVisibility(View.GONE);
        }

        return isValid;
    }

    private String convertDayMonthToString(int value){
        return value < 10 ?  "0" + value : "" + value;
    }

}