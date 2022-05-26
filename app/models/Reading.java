package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import weathertop.weather.instrument.Anemometer;
import weathertop.weather.instrument.Compass;
import weathertop.weather.instrument.Thermometer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

@Entity
public class Reading extends Model
{
    public int code;
    public float temperature;
    public float windSpeed;
    public int windPressure;
    public float windDirection;
    public Date date;


    public Reading(Date date, int code, float temperature, float windSpeed, int windPressure, float windDirection)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windPressure = windPressure;
        this.windDirection = windDirection;
        this.date = date;
    }

    public String getDate(){
        return  String.format("%tF %<tT", date);
    }

    public float getCelsiusToFahrenheit(){
        Thermometer thermometer = new Thermometer();
        return thermometer.getCelsiusToFahrenheit(this);
    }

    public double getWindChill(){
        Thermometer thermometer = new Thermometer();
        return thermometer.getWindChill(this);
    }

    public String getWeatherCode(){
        Anemometer anemometer = new Anemometer();
        return anemometer.getWeatherCode(this);
    }

    public Integer getBeaufortScale(){
        Anemometer anemometer = new Anemometer();
        return anemometer.getBeaufortScale(this);
    }

    public String getWeatherCodeIcon(Reading reading){
        Anemometer anemometer = new Anemometer();
        return anemometer.getWeatherCodeIcon(this);
    }

    //When this function was named "getWindDirection()", the returned value overwrote all windDirection variables?
    /* After restructuring code into separate instrument classes I renamed the function getWindDirection() -
    A call to getWindDirection would cause StackOverflow Error - see Notes */
    public String getCompassWindDirection(){
        Compass compass = new Compass();
        return compass.getCompassWindDirection(this);
    }

    public HashMap<String, String> getReadingAsHashMap(){
        HashMap<String, String> readingAsHashMap = new HashMap<>();
        readingAsHashMap.put("code", String.valueOf(this.code));
        readingAsHashMap.put("windSpeed", String.valueOf(this.windSpeed));
        readingAsHashMap.put("windDirection", String.valueOf(this.windDirection));
        readingAsHashMap.put("windPressure", String.valueOf(this.windPressure));
        readingAsHashMap.put("Temp", String.valueOf(this.temperature));
        return readingAsHashMap;
    }
}

