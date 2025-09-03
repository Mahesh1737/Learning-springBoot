package com.mrm.Weather_App.Contoller;

import com.mrm.Weather_App.Service.WeatherService;
import com.mrm.Weather_App.dto.MyForcastResponse;
import com.mrm.Weather_App.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@CrossOrigin
public class Controller {

    @Autowired
    public WeatherService service;

    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city){
        return service.test();
    }

    @GetMapping("/my/{city}")
    public WeatherResponse getWeatherData(@PathVariable String city){
        return service.getData(city);
    }

    @GetMapping("/forcast")
    public MyForcastResponse getForcastData(@RequestParam String city, @RequestParam int days){
        return service.getForcast(city, days);
    }
}
