package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;


public class Dashboard extends Controller
{
  public static void index()
  {
    List<Station> stations = Station.findAll();
    Logger.info("Rendering Dashboard");
    render ("dashboard.html", stations);
  }

  public static void addStation (String name, float latitude, float longitude)
  {
    Station station = new Station (name, latitude, longitude);
    Logger.info ("Adding a new Station called " + name);
    station.save();
    redirect ("/dashboard");
  }
}