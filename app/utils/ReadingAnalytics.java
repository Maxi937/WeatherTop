package utils;

import models.Reading;

import java.util.List;

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
}
