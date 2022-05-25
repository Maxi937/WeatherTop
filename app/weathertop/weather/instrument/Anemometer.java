package weathertop.weather.instrument;

import models.Reading;

import java.util.ArrayList;
import java.util.HashMap;


public class Anemometer extends WeatherInstrument {

    ArrayList<HashMap<String, String>> weatherCodeData;
    ArrayList<HashMap<String, Integer>> beaufortScaleData;

    public Anemometer() {
        weatherCodeData = (ArrayList<HashMap<String, String>>) weatherInstrumentData.get("weather");
        beaufortScaleData = (ArrayList<HashMap<String, Integer>>) weatherInstrumentData.get("beaufortScale");
    }

    public String getWeatherCode(Reading reading) {
        String weatherCode = null;

        for (int i = 0; i <= weatherCodeData.size() - 1; i++) {
            HashMap<String, String> dataMap;
            dataMap = weatherCodeData.get(i);

            if (dataMap.containsKey("code")) {
                weatherCode = dataMap.get("code");
            }
            if (dataMap.containsKey("weather") && Integer.parseInt(weatherCode) == reading.code){
                return dataMap.get("weather");
            }
        }
        return null;
    }

    public String getWeatherCodeIcon(Reading reading) {
        String weatherCode = null;

        for (int i = 0; i <= weatherCodeData.size() - 1; i++) {
            HashMap<String, String> dataMap;
            dataMap = weatherCodeData.get(i);

            if (dataMap.containsKey("code")) {
                weatherCode = dataMap.get("code");
            }
            if (dataMap.containsKey("icon") && Integer.parseInt(weatherCode) == reading.code){
                return dataMap.get("icon");
            }
        }
        return null;
    }

    public Integer getBeaufortScale(Reading reading) {
        int min = 0;
        int max = 0;
        HashMap<String, Integer> dataMap = new HashMap<>();


        for (int i = 0; i <= beaufortScaleData.size() - 1; i++) {
            dataMap = beaufortScaleData.get(i);

            if (dataMap.containsKey("min")) {
                min = dataMap.get("min");
            }

            if (dataMap.containsKey("max")) {
                max = dataMap.get("max");
            }

            if (reading.windSpeed >= min && reading.windSpeed <= max) {
                return dataMap.get("beaufort");
            }
        }
        return dataMap.get("beaufort");
    }

}
