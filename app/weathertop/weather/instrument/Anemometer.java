package weathertop.weather.instrument;

import models.Reading;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* TODO: Refactor getBeaufortWindSpeed - Make it an array list of Hash Maps like how the wind direction is done */
public class Anemometer extends WeatherInstrument{

    HashMap<Integer, String> weatherCodeData;
    LinkedHashMap<Integer, String> beaufortScaleData;

    public Anemometer(){
        weatherCodeData = (HashMap<Integer, String>) weatherInstrumentData.get("weather");
        beaufortScaleData = (LinkedHashMap<Integer, String>) weatherInstrumentData.get("beaufortScale");
    }

    public String getWeatherCode(Reading reading){
        String weatherCode;
        weatherCode = weatherCodeData.get(reading.code);
        return weatherCode;
    }

    public Integer getBeaufortWindSpeed(Reading reading){
        int index = 0;
        for (Integer i : beaufortScaleData.keySet()) {

            if (reading.windSpeed <= i) {
                int maxSpeed;
                maxSpeed = i - 1;
                if(reading.windSpeed <= maxSpeed){
                    return index;
                }
                return index;
            }
            index++;
        }
        return index;
    }

}
