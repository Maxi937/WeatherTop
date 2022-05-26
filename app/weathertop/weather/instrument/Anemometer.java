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
        for (int i = 0; i <= weatherCodeData.size() - 1; i++) {
            HashMap<String, String> dataMap;
            dataMap = weatherCodeData.get(i);

            if (Integer.parseInt(dataMap.get("code")) == reading.code) {
                return dataMap.get("weather");
            }
        }
        return null;
    }

    public String getWeatherCodeIcon(Reading reading) {
        for (int i = 0; i <= weatherCodeData.size() - 1; i++) {
            HashMap<String, String> dataMap;
            dataMap = weatherCodeData.get(i);

            if (Integer.parseInt(dataMap.get("code")) == reading.code) {
                return dataMap.get("icon");
            }
        }
        return null;
    }

    public Integer getBeaufortScale(Reading reading) {
        HashMap<String, Integer> dataMap = new HashMap<>();

        for (int i = 0; i <= beaufortScaleData.size() - 1; i++) {
            dataMap = beaufortScaleData.get(i);

            if (reading.windSpeed >= dataMap.get("min") && reading.windSpeed <= dataMap.get("max")) {
                return dataMap.get("beaufort");
            }
        }
        return dataMap.get("beaufort");
    }
}
