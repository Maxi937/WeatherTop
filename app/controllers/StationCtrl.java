package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Station id = " + id);
        render("station.html",station);
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
