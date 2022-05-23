package utils;

import models.Reading;

public class Thermometer {

    public Thermometer(){

    }

    public double getCelsiusToFahrenheit(Reading reading){
        double temperatureAsCelsius = reading.temperature;
        return temperatureAsCelsius * 9/5 + 32;
    }

    public double getWindChill(Reading reading){
        double temperatureAsCelsius = reading.temperature;
        double windSpeed = reading.windSpeed;
        double v = Math.pow(windSpeed, 0.16);

        return 13.12 + (.6215 * temperatureAsCelsius) - (11.37 * v) + (.3965 * temperatureAsCelsius * v);
    }


}
