package utils;

import models.Reading;
import org.yaml.snakeyaml.Yaml;

import java.util.*;
import java.io.InputStream;

//ToDo: the maps.yaml is loaded 3 times, only needed once - refactor
//ToDo: Add Comments

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

        //ToDO: Load YAML - Move to Function as this is done three times in this class
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

        //ToDO: Load YAML - Move to Function as this is done three times in this class
        Yaml yaml = new Yaml();
        InputStream inputStream = ReadingAnalytics.class
                .getClassLoader()
                .getResourceAsStream("maps.yml");
        Map<String, Object> data = (Map<String, Object>) yaml.load(inputStream);

        beaufortHashMap = (LinkedHashMap<Integer, String>) data.get("beaufort");

        int index = 0;
        for (Integer i : beaufortHashMap.keySet()) {
            //System.out.println("i: " + i);

            if (windSpeed <= i) {
                int maxSpeed;
                maxSpeed = i - 1;
                //System.out.println("max speed: " + maxSpeed);
                if(windSpeed <= maxSpeed){
                    //System.out.println("beaufort: " + index);
                    return index;
                }
                return index;
            }
            index++;
        }
        return index;
    }

    public static String getWindDirection(double windDirection){
        String weatherDirectionResult = null;

        //ToDO: Load YAML - Move to Function as this is done three times in this class
        ArrayList<HashMap<String, String>> windDirectionHashMap;

        Yaml yaml = new Yaml();
        InputStream inputStream = ReadingAnalytics.class
                .getClassLoader()
                .getResourceAsStream("maps.yml");
        Map<String, Object> data = (Map<String, Object>) yaml.load(inputStream);

        //ToDO: Change variable name - this is no longer a hash map, this line takes the windDirection portion of the YAML and loads is to an Array of Hash Maps
        windDirectionHashMap = (ArrayList<HashMap<String, String>>) data.get("windDirection");

        System.out.println("Direction to check: "+ windDirection);

        for (HashMap<String, String> stringStringHashMap : windDirectionHashMap) {
            WindReading windReading = new WindReading(stringStringHashMap);
            if (windReading.checkWindDirection(windDirection)) {
                System.out.println(windDirection + " is between " + windReading.getWindDirectionMin() + " - " + windReading.getWindDirectionMax());
                weatherDirectionResult = windReading.getWindDirection();
            }
        }
        System.out.println("Result:" + weatherDirectionResult);
        return weatherDirectionResult;
    }
}
