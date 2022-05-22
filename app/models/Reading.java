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

    public Reading(int code, double temperature, double windSpeed, int windPressure)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windPressure = windPressure;
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
}

