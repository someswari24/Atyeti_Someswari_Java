package com.weather_Analyzer.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather{

	@JsonProperty("weather")
	private List<WeatherItem> weather;

	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	@Override
 	public String toString(){
		return 
			"Weather{" + 
			"weather = '" + weather + '\'' + 
			"}";
		}
}