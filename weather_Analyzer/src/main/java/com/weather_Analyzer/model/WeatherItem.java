package com.weather_Analyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherItem{

	@JsonProperty("date")
	private String date;

	@JsonProperty("condition")
	private String condition;

	@JsonProperty("city")
	private String city;

	@JsonProperty("temperature")
	private Object temperature;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("windSpeed")
	private Object windSpeed;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setCondition(String condition){
		this.condition = condition;
	}

	public String getCondition(){
		return condition;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setTemperature(Object temperature){
		this.temperature = temperature;
	}

	public Object getTemperature(){
		return temperature;
	}

	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	public int getHumidity(){
		return humidity;
	}

	public void setWindSpeed(Object windSpeed){
		this.windSpeed = windSpeed;
	}

	public Object getWindSpeed(){
		return windSpeed;
	}

	@Override
 	public String toString(){
		return 
			"WeatherItem{" + 
			"date = '" + date + '\'' + 
			",condition = '" + condition + '\'' + 
			",city = '" + city + '\'' + 
			",temperature = '" + temperature + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",windSpeed = '" + windSpeed + '\'' + 
			"}";
		}
}