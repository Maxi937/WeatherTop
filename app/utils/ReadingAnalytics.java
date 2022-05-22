package utils;

import models.Reading;
import org.yaml.snakeyaml.Yaml;

import java.util.*;

import java.io.InputStream;
import java.util.stream.Collectors;


public class ReadingAnalytics {
    public static Reading getLastReading(List<Reading> readings){
        Reading lastReading = null;

        if (readings.size()>0) {
            lastReading = readings.get(readings.size()-1);
        }

        return lastReading;
    }

    public static double getCelsToFar(double tempCels){
        double tempFar;
        tempFar = tempCels * 9/5 + 32;
        return tempFar;
    }

    public static String getWeatherCode(int weatherCode){
        String weatherCodeString = null;

        HashMap<String, String> weatherCodes = new HashMap<String, String>();

        Yaml yaml = new Yaml();
        InputStream inputStream = ReadingAnalytics.class
                .getClassLoader()
                .getResourceAsStream("maps.yml");
        Map<String, Object> data = (Map<String, Object>) yaml.load(inputStream);

        weatherCodes = (HashMap<String, String>) data.get("weather");

        //System.out.println(data.get("weather"));

        weatherCodeString = weatherCodes.get(weatherCode);

        //System.out.println(weatherCodeString);
        return weatherCodeString;
    }

    public static Integer getBeaufortWindSpeed(double windSpeed){
        int beaufortInt;
        int beaufortMax;
        HashMap<String, Integer> beaufortHashMap;

        List<String> beaufortReading;

        Yaml yaml = new Yaml();
        InputStream inputStream = ReadingAnalytics.class
                .getClassLoader()
                .getResourceAsStream("maps.yml");
        Map<String, Object> data = (Map<String, Object>) yaml.load(inputStream);

        beaufortHashMap = (HashMap<String, Integer>) data.get("beaufort");

        System.out.println(beaufortHashMap);
        //System.out.println(keySet);
        //System.out.println(beaufortReading.contains(1));
        //System.out.println(beaufortReading.size());

        beaufortInt = beaufortHashMap.get(5);
        System.out.println(beaufortInt);

        //System.out.println(weatherCodeString);
        return beaufortInt;
    }

}
