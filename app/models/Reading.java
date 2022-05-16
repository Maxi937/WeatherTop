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
}

