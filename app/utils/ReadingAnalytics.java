package utils;

import models.Reading;
import org.yaml.snakeyaml.Yaml;

import java.util.*;
import java.io.InputStream;


public class ReadingAnalytics {

    public static Reading getLastReading(List<Reading> readings){
        Reading lastReading = null;

        if (readings.size()>0) {
            lastReading = readings.get(readings.size()-1);
        }

        return lastReading;
    }

    public static double getCelsToFar(double temperatureAsCelsius){
        return temperatureAsCelsius * 9/5 + 32;
    }

    public static double getWindChill(double temperatureAsCelsius, double windSpeed){
        double v = Math.pow(windSpeed, 0.16);
        return 13.12 + (.6215 * temperatureAsCelsius) - (11.37 * v) + (.3965 * temperatureAsCelsius * v);
    }

    public static String getWeatherCode(int weatherCode){

        String weatherCodeString;

        HashMap<Integer, String> weatherCodes;

        Yaml yaml = new Yaml();
        InputStream inputStream = ReadingAnalytics.class
                .getClassLoader()
                .getResourceAsStream("maps.yml");
        Map<String, Object> data = (Map<String, Object>) yaml.load(inputStream);

        weatherCodes = (HashMap<Integer, String>) data.get("weather");

        //System.out.println(data.get("weather"));

        weatherCodeString = weatherCodes.get(weatherCode);

        //System.out.println(weatherCodeString);
        return weatherCodeString;
    }

    public static Integer getBeaufortWindSpeed(double windSpeed){

        LinkedHashMap<Integer, String> beaufortHashMap;

        Yaml yaml = new Yaml();
        InputStream inputStream = ReadingAnalytics.class
                .getClassLoader()
                .getResourceAsStream("maps.yml");
        Map<String, Object> data = (Map<String, Object>) yaml.load(inputStream);

        beaufortHashMap = (LinkedHashMap<Integer, String>) data.get("beaufort");

        int index = 0;
        for (Integer i : beaufortHashMap.keySet()) {
            System.out.println("i: " + i);

            if (windSpeed <= i) {
                int maxSpeed;
                maxSpeed = i - 1;
                System.out.println("max speed: " + maxSpeed);
                if(windSpeed <= maxSpeed){
                    System.out.println("beaufort: " + index);
                    return index;
                }
                return index;
            }
            index++;
        }
        return index;
    }
}
