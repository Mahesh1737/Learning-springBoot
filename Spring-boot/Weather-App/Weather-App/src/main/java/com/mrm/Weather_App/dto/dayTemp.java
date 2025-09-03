package com.mrm.Weather_App.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class dayTemp {
    private String date;
    private double maxTemp;
    private double avgTemp;
    private double minTemp;

    public dayTemp(String date, double maxTemp, double avgTemp, double minTemp) {
        this.date = date;
        this.maxTemp = maxTemp;
        this.avgTemp = avgTemp;
        this.minTemp = minTemp;
    }

    public dayTemp() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }
}
