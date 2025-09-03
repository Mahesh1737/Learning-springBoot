package com.mrm.Weather_App.dto;

import java.util.ArrayList;
import java.util.List;

public class Forecast{
    public ArrayList<Forecastday> forecastday;

    public Forecast(ArrayList<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }
    public Forecast(){

    }

    public ArrayList<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }
}