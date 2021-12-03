package com.example.android.simplefit.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.simplefit.data.dao.BodyDataDAO;
import com.example.android.simplefit.data.dao.SimpleFitDatabase;
import com.example.android.simplefit.models.db.BodyData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BodyDataRepository {

    private static BodyDataRepository instance;
    private BodyDataDAO bodyDataDAO;
    private ExecutorService executorService;
    private LiveData<List<BodyData>> allBodyData;

    private BodyDataRepository(Application application)
    {
        bodyDataDAO = SimpleFitDatabase.getInstance(application).bodyDataDAO();
        allBodyData = bodyDataDAO.getAllData();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static BodyDataRepository getInstance(Application application){
        if(instance == null) {
            instance = new BodyDataRepository(application);
        }

        return instance;
    }

    public void addBodyData(BodyData bodyData) {
        executorService.execute(() -> bodyDataDAO.insert(bodyData)) ;
    }

    public LiveData<List<BodyData>> getBodyData()
    {
        allBodyData = bodyDataDAO.getAllData();
        return allBodyData;
    }

    public void updateBodyData(BodyData bodyData)
    {
        executorService.execute(() -> bodyDataDAO.update(bodyData));
    }

    public void deleteBodyData(BodyData bodyData)
    {
        executorService.execute(() -> bodyDataDAO.delete(bodyData));
    }

}
