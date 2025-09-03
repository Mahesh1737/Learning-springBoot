package com.mrm.Weather_App.Service;

import com.mrm.Weather_App.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    public String test(){
        return "Rainy";
    }

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private RestTemplate template = new RestTemplate();

    public WeatherResponse getData(String city){
        String url = apiUrl+"?key="+apiKey+"&q="+city;
        Root response = template.getForObject(url, Root.class);
        WeatherResponse weatherResponse = new WeatherResponse();
        String c = response.getLocation().getName();
        weatherResponse.setCity(response.getLocation().getName());
        weatherResponse.setCountry(response.getLocation().getCountry());
        weatherResponse.setRegion(response.getLocation().getRegion());
        String condition = response.getCurrent().getCondition().getText();
        weatherResponse.setCondition(condition);
        weatherResponse.setTemperature(response.getCurrent().getTemp_c());

        return weatherResponse;
    }




    @Value("${weather.api.forcast.url}")
    private String forcastapiUrl;

    public MyForcastResponse getForcast(String city, int days){
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse = getData(city);

        MyForcastResponse response = new MyForcastResponse();
        response.setWeatherResponse(weatherResponse);

        String url = forcastapiUrl+"?key="+apiKey+"&q="+city+"&days="+days;
        Root apiresponse = template.getForObject(url, Root.class);

        List<dayTemp> dayList = new ArrayList<>();

        Forecast forecast = apiresponse.getForecast();
        ArrayList<Forecastday> forecastdays = forecast.getForecastday();
        for(Forecastday day: forecastdays){
            dayTemp d = new dayTemp();
            d.setDate(day.getDate());
            d.setMinTemp(day.getDay().mintemp_c);
            d.setAvgTemp(day.getDay().avgtemp_c);
            d.setMaxTemp(day.getDay().maxtemp_c);
            dayList.add(d);
        }

        response.setDayTemp(dayList);
        return response;


    }
}
