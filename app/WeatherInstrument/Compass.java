package WeatherInstrument;

import models.Reading;

import java.util.ArrayList;
import java.util.HashMap;


public class Compass extends WeatherInstrument {

    public Compass() {
    }

    public String getWindDirection(Reading reading){
        String weatherDirectionResult = null;
        ArrayList<HashMap<String, String>> compassData;
        compassData = (ArrayList<HashMap<String, String>>) weatherInstrumentData.get("compass");

        for (HashMap<String, String> compassDataMap : compassData) {
            double compassDegreeMin = Double.parseDouble(compassDataMap.get("min"));
            double compassDegreeMax = Double.parseDouble(compassDataMap.get("max"));
            String compassResult = compassDataMap.get("direction");

            if (reading.windDirection >= compassDegreeMin && reading.windDirection <= compassDegreeMax) {
                weatherDirectionResult = compassResult;
            }
        }
        return weatherDirectionResult;
    }







}
