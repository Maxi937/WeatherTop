package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import weathertop.weather.instrument.Anemometer;
import weathertop.weather.instrument.Compass;
import weathertop.weather.instrument.Thermometer;
import weathertop.weather.instrument.WeatherInstrument;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Station id = " + id);

        Thermometer thermometer = new Thermometer();
        Anemometer anemometer = new Anemometer();
        Compass compass = new Compass();

        render("station.html",station, thermometer, anemometer, compass);
    }

    public static void addReading(Long id, int code, float temperature, float windSpeed, int windPressure, float windDirection)
    {
        Date date = new Date();
        Reading reading = new Reading(date, code, temperature, windSpeed, windPressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/station/" + id);
    }
}
