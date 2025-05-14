package TemperatureConverter;

public class TemperatureConverterMain {
    public static void main(String[] args) {
        TemperatureConverter temperatureConverter=new TemperatureConverter(34.3);

        double celsius= temperatureConverter.getCelsius();
        double fahrenheit= temperatureConverter.celsiusToFahrenheit(celsius);

        System.out.println(celsius+" °C ="+fahrenheit+" °F");
    }

}
