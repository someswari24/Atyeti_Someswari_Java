package com.weather_Analyzer.service;

import com.weather_Analyzer.model.WeatherItem;
import com.weather_Analyzer.util.WeatherAnalyzer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {
   private List<WeatherItem> weatherItemList;

//    public WeatherService(List<WeatherItem> weatherItemList) {
//        this.weatherItemList = WeatherAnalyzer.weatherHistoryAnalyzer();
//    }

    @PostConstruct
    public void init(){
        weatherItemList = WeatherAnalyzer.weatherHistoryAnalyzer();
    }

    public Map<String, Optional<WeatherItem>> highestTempCity(){
        return weatherItemList.stream().filter(item->item.getTemperature() instanceof Number)
                .collect(Collectors.groupingBy(WeatherItem::getCity,
                        Collectors.maxBy(Comparator.comparingDouble
                                (item->((Number)item.getTemperature()).doubleValue()))));
    }

//    public Map<String,Double> highestTempCity(){
//        return weatherItemList.stream().filter(item->item.getTemperature() instanceof Number)
//                .collect(Collectors.groupingBy(WeatherItem::getCity,
//                        Collectors.collectingAndThen(
//                        Collectors.maxBy(Comparator.comparingDouble
//                                (item->((Number)item.getTemperature()).doubleValue())),
//                                opt->opt.map(item->((Number)item.getTemperature()).doubleValue()).orElse(0.0))));
//    }

    public Map<String,Double> avgTempByCity(){
        return weatherItemList.stream().filter(item->item.getTemperature() instanceof Number)
                .collect(Collectors.groupingBy(WeatherItem::getCity,
                        Collectors.averagingDouble(item->((Number)item.getTemperature()).doubleValue())));
    }

    public List<WeatherItem> RainyOrCloudy(String condition){
        return weatherItemList.stream().filter(item->item.getCondition()
                .equalsIgnoreCase(condition)).collect(Collectors.toList());
    }

    public List<WeatherItem>windSpeedAboveThresh(Double threshold){
        return weatherItemList.stream().filter(item->item.getWindSpeed() instanceof Number)
                .filter(item->((Number)item.getWindSpeed()).doubleValue() > threshold)
                .collect(Collectors.toList());

    }

    public Map<String,List<WeatherItem>> weatherCondition(){
        return weatherItemList.stream().collect(Collectors.groupingBy(WeatherItem::getCondition));
    }
}
