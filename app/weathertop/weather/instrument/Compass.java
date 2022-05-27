package weathertop.weather.instrument;

import models.Reading;

import java.util.ArrayList;
import java.util.HashMap;


public class Compass extends WeatherInstrument {

    private ArrayList<HashMap<String, String>> compassData;

    public Compass() {
        compassData = (ArrayList<HashMap<String, String>>) weatherInstrumentData.get("compass");
    }

    public String getCompassWindDirection(Reading reading) {
        for (HashMap<String, String> compassDataMap : compassData) {
            double compassDegreeMin = Double.parseDouble(compassDataMap.get("min"));
            double compassDegreeMax = Double.parseDouble(compassDataMap.get("max"));
            String compassDirection = compassDataMap.get("direction");

            if (reading.windDirection >= compassDegreeMin && reading.windDirection <= compassDegreeMax) {
                return compassDirection;
            }

            //catch for last case - North is between 348.15 - 11.15
            if (reading.windDirection >= 348.75 || reading.windDirection <= 11.25) {
                return compassDataMap.get("direction");
            }
        }
        return null;
    }


}
