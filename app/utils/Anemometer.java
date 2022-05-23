package utils;

import models.Reading;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* TODO: Refactor getBeaufortWindSpeed - Make it an array list of Hash Maps like how the wind direction is done */

public class Anemometer {
    private final Map<String, Object> anemometerData;

    public Anemometer(){
        this.anemometerData = InstrumentUtil.getData();
    }

    public String getWeatherCode(Reading reading){
        String weatherCodeString;
        HashMap<Integer, String> weatherCodeData;

        weatherCodeData = (HashMap<Integer, String>) this.anemometerData.get("weather");
        weatherCodeString = weatherCodeData.get(reading.code);

        return weatherCodeString;
    }

    public Integer getBeaufortWindSpeed(Reading reading){
        double windSpeed = reading.windSpeed;
        LinkedHashMap<Integer, String> beaufortScaleData;
        beaufortScaleData = (LinkedHashMap<Integer, String>) this.anemometerData.get("beaufort");

        int index = 0;
        for (Integer i : beaufortScaleData.keySet()) {

            if (windSpeed <= i) {
                int maxSpeed;
                maxSpeed = i - 1;
                if(windSpeed <= maxSpeed){
                    return index;
                }
                return index;
            }
            index++;
        }
        return index;
    }

}
