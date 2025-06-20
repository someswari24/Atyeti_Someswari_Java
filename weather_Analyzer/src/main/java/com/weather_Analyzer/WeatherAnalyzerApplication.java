package com.weather_Analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.weather_Analyzer.WeatherAnalyzerApplication",
		"com.weather_Analyzer.controller",
		"com.weather_Analyzer.service",
		"com.weather_Analyzer.util",
		"com.weather_Analyzer.model"
})
public class WeatherAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAnalyzerApplication.class, args);
	}

}