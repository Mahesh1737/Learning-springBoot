package com.mrm.Weather_App.dto;

import java.util.List;

public class Forecastday{
    public String date;
    public int date_epoch;
    public Day day;
    public Astro astro;
    public List<Hour> hour;

    public Forecastday(String date, int date_epoch, Day day, Astro astro, List<Hour> hour) {
        this.date = date;
        this.date_epoch = date_epoch;
        this.day = day;
        this.astro = astro;
        this.hour = hour;
    }

    public Forecastday() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDate_epoch() {
        return date_epoch;
    }

    public void setDate_epoch(int date_epoch) {
        this.date_epoch = date_epoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public List<Hour> getHour() {
        return hour;
    }

    public void setHour(List<Hour> hour) {
        this.hour = hour;
    }
}