package WeatherInstrument;

import models.Reading;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* TODO: Refactor getBeaufortWindSpeed - Make it an array list of Hash Maps like how the wind direction is done */
public class Anemometer extends WeatherInstrument{

    public Anemometer(){
    }

    public String getWeatherCode(Reading reading){
        String weatherCode;
        HashMap<Integer, String> weatherCodeData;

        weatherCodeData = (HashMap<Integer, String>) weatherInstrumentData.get("weather");
        weatherCode = weatherCodeData.get(reading.code);

        return weatherCode;
    }

    public Integer getBeaufortWindSpeed(Reading reading){
        LinkedHashMap<Integer, String> beaufortScaleData;
        beaufortScaleData = (LinkedHashMap<Integer, String>) weatherInstrumentData.get("beaufortScale");

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
