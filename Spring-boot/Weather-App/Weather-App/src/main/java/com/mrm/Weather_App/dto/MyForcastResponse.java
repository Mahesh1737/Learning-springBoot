package com.mrm.Weather_App.dto;

import java.util.List;

public class MyForcastResponse {

    private WeatherResponse weatherResponse;
    private List<dayTemp> dayTemp;

    public MyForcastResponse(WeatherResponse weatherResponse, List<dayTemp> dayTemp) {
        this.weatherResponse = weatherResponse;
        this.dayTemp = dayTemp;
    }

    public MyForcastResponse() {

    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public List<dayTemp> getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(List<dayTemp> dayTemp) {
        this.dayTemp = dayTemp;
    }
}
