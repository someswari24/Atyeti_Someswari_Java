package TemperatureConverter;

public class TemperatureConverter {
    private double celsius;

    public TemperatureConverter(double celsius) {
        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public double celsiusToFahrenheit(double celsius){
        double Fahrenheit=0.0;
        Fahrenheit=(celsius*(9.0/5))+32;
        return Fahrenheit;
    }

    @Override
    public String toString() {
        return "TemperatureConverter{" +
                "celsius=" + celsius +
                '}';
    }
}
