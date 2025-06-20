package com.weather_Analyzer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather_Analyzer.model.WeatherItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WeatherAnalyzer {
    public static List<WeatherItem> weatherHistoryAnalyzer()  {
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream = WeatherAnalyzer.class.getClassLoader()
                    .getResourceAsStream("weather.json");
           List<WeatherItem> weatherItems = objectMapper.readValue(inputStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class,WeatherItem.class));

            return weatherItems;

        } catch (IOException e) {
            throw new RuntimeException("Error loading weather data",e);
        }
    }
}


