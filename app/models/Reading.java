package models;

import javax.persistence.Entity;
import play.db.jpa.Model;
import utils.Anemometer;
import utils.Compass;
import utils.Thermometer;

@Entity
public class Reading extends Model
{
    public int code;
    public double temperature;
    public double windSpeed;
    public int windPressure;
    public double windDirection;


    public Reading(int code, double temperature, double windSpeed, int windPressure, double windDirection)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windPressure = windPressure;
        this.windDirection = windDirection;
    }

    public double getCelsiusToFahrenheit(){
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

    public Integer getBeaufortWindSpeed(){
        Anemometer anemometer = new Anemometer();
        return anemometer.getBeaufortWindSpeed(this);
    }

    //When this function was named "getWindDirection()", the returned value overwrote all windDirection variables?
    /* This Function is Evil - After restructuring code into separate instrument classes I renamed the function getWindDirection() -
    A call to getWindDirection would cause StackOverflow Error - see Notes */
    public String getCompassDirection(){
        Compass compass = new Compass();
        return compass.getWindDirection(this);
    }
}

