package com.example.android.simplefit.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.simplefit.models.db.BodyData;

import java.util.List;

@Dao
public interface BodyDataDAO {

    @Insert
    void insert(BodyData bodyData);

    @Update
    void update(BodyData bodyData);

    @Delete
    void delete (BodyData bodyData);

    @Query("SELECT * FROM bodyData")
    LiveData<List<BodyData>> getAllData();

}
