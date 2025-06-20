package com.weather_Analyzer.controller;

import com.weather_Analyzer.model.WeatherItem;
import com.weather_Analyzer.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/highest-temp-by-city")
    public Map<String, Optional<WeatherItem>> highestTempCity(){
        return weatherService.highestTempCity();
    }

    @GetMapping("/average-temp")
    public Map<String,Double> avgTempByCity(){
        return weatherService.avgTempByCity();
    }

    @GetMapping("/condition/{condition}")
    public List<WeatherItem> RainyOrCloudy(@PathVariable String condition){
        return weatherService.RainyOrCloudy(condition);
    }

    @GetMapping("/wind-alert/{threshold}")
    public List<WeatherItem>windSpeedAboveThresh(@PathVariable Double threshold){
        return weatherService.windSpeedAboveThresh(threshold);
    }

    @GetMapping("/grouped-by-condition")
    public Map<String,List<WeatherItem>> weatherCondition(){
        return weatherService.weatherCondition();
    }
}
