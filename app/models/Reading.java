package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

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

    public double getFarTemp(){
       return utils.ReadingAnalytics.getCelsToFar(this.temperature);
    }

    public String getWeatherCode(){
        return utils.ReadingAnalytics.getWeatherCode(this.code);
    }

    public Integer getBeaufortWindSpeed(){
        return utils.ReadingAnalytics.getBeaufortWindSpeed(this.windSpeed);
    }

    public double getWindChill(){
        return utils.ReadingAnalytics.getWindChill(this.temperature, this.windSpeed);
    }

    //When this function was named "getWindDirection()", the returned value overwrote all windDirection variables?
    public String getWindDirectionFacing(){
        return utils.ReadingAnalytics.getWindDirection(this.windDirection);
    }
}

