package controllers;

import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import weathertop.weather.instrument.Anemometer;
import weathertop.weather.instrument.Compass;
import weathertop.weather.instrument.Thermometer;
import weathertop.weather.instrument.WeatherInstrument;


public class Dashboard extends Controller
{
  public static void index()
  {
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;

    Thermometer thermometer = new Thermometer();
    Anemometer anemometer = new Anemometer();
    Compass compass = new Compass();

    Logger.info("Rendering Dashboard");
    render ("dashboard.html", stations, thermometer, anemometer, compass);
  }

  public static void addStation (String name, float latitude, float longitude)
  {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station (name, latitude, longitude);
    member.stations.add(station);
    member.save();
    Logger.info ("Adding a new Station called " + name);
    redirect ("/dashboard");
  }

  public static void deleteStation (Long id)
  {
    Logger.info("Deleting a Station");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect ("/dashboard");
  }
}