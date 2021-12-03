package com.example.android.simplefit.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.simplefit.data.repositories.BodyDataRepository;
import com.example.android.simplefit.models.db.BodyData;

import java.util.List;

public class BodyDataViewModel extends AndroidViewModel {

    private BodyDataRepository bodyDataRepository;

    public BodyDataViewModel(@NonNull Application application) {
        super(application);
        bodyDataRepository = BodyDataRepository.getInstance(application);
    }

    public void addBodyData(BodyData bodyData) {
        bodyDataRepository.addBodyData(bodyData);
    }

    public void updateBodyData(BodyData bodyData) {
        bodyDataRepository.updateBodyData(bodyData);
    }

    public void deleteBodyData(BodyData bodyData) {
        bodyDataRepository.deleteBodyData(bodyData);
    }

    public LiveData<List<BodyData>> getAllBodyData(){
        return bodyDataRepository.getBodyData();
    }
}
