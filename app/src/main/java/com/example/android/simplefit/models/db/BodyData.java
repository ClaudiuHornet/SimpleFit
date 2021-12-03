package com.example.android.simplefit.models.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "bodyData")
public class BodyData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int bodyData_id;
    private float weight;
    private int armMeasurement;
    private int chestMeasurement;
    private int waistMeasurement;
    private int legMeasurement;
    private String date;

    public BodyData(float weight, int armMeasurement, int chestMeasurement, int waistMeasurement, int legMeasurement, String date) {
        this.weight = weight;
        this.armMeasurement = armMeasurement;
        this.chestMeasurement = chestMeasurement;
        this.waistMeasurement = waistMeasurement;
        this.legMeasurement = legMeasurement;
        this.date = date;
    }

    @Ignore
    public BodyData(int bodyData_id, float weight, int armMeasurement, int chestMeasurement, int waistMeasurement, int legMeasurement, String date) {
        this.bodyData_id = bodyData_id;
        this.weight = weight;
        this.armMeasurement = armMeasurement;
        this.chestMeasurement = chestMeasurement;
        this.waistMeasurement = waistMeasurement;
        this.legMeasurement = legMeasurement;
        this.date = date;
    }

    public int getBodyData_id() {
        return bodyData_id;
    }

    public void setBodyData_id(int bodyData_id) {
        this.bodyData_id = bodyData_id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getArmMeasurement() {
        return armMeasurement;
    }

    public void setArmMeasurement(int armMeasurement) {
        this.armMeasurement = armMeasurement;
    }

    public int getChestMeasurement() {
        return chestMeasurement;
    }

    public void setChestMeasurement(int chestMeasurement) {
        this.chestMeasurement = chestMeasurement;
    }

    public int getWaistMeasurement() {
        return waistMeasurement;
    }

    public void setWaistMeasurement(int waistMeasurement) {
        this.waistMeasurement = waistMeasurement;
    }

    public int getLegMeasurement() {
        return legMeasurement;
    }

    public void setLegMeasurement(int legMeasurement) {
        this.legMeasurement = legMeasurement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BodyData{" +
                "bodyData_id=" + bodyData_id +
                ", weight=" + weight +
                ", armMeasurement=" + armMeasurement +
                ", chestMeasurement=" + chestMeasurement +
                ", waistMeasurement=" + waistMeasurement +
                ", legMeasurement=" + legMeasurement +
                '}';
    }
}
