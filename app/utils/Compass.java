package utils;

import models.Reading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Compass {
    private final Map<String, Object> compassData;

    public Compass() {
        this.compassData = InstrumentUtil.getData();
    }

    public String getWindDirection(Reading reading){
        String weatherDirectionResult = null;
        ArrayList<HashMap<String, String>> compassData;
        compassData = (ArrayList<HashMap<String, String>>) this.compassData.get("compass");

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
