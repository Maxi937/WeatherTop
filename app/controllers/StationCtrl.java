package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import weatherinstrumentpckg.Anemometer;
import weatherinstrumentpckg.Compass;
import weatherinstrumentpckg.Thermometer;

import java.util.Date;

public class StationCtrl extends Controller {
  public static void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Station id = " + id);

    Thermometer thermometer = new Thermometer();
    Anemometer anemometer = new Anemometer();
    Compass compass = new Compass();

    render("station.html", station, thermometer, anemometer, compass);
  }

  public static void addReading(Long id, int code, float temperature, float windSpeed, int windPressure, float windDirection) {
    if (code == 0 || temperature == 0 || windSpeed == 0 || windPressure == 0) {
      Logger.info("All fields not completed");
      redirect("/station/" + id);
    }

    Date date = new Date(System.currentTimeMillis());
    Reading reading = new Reading(date, code, temperature, windSpeed, windPressure, windDirection);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/station/" + id);
  }

  public static void deleteReading(Long id, Long readingId) {
    Logger.info("Deleting a Reading: " + readingId);
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingId);
    station.readings.remove(reading);
    station.save();
    reading.delete();

    Thermometer thermometer = new Thermometer();
    Anemometer anemometer = new Anemometer();
    Compass compass = new Compass();
    render("station.html", station, thermometer, anemometer, compass);
  }


}
