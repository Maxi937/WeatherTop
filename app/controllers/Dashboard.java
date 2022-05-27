package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import weathertop.weather.instrument.Anemometer;
import weathertop.weather.instrument.Compass;
import weathertop.weather.instrument.Thermometer;

import java.util.Comparator;
import java.util.List;


public class Dashboard extends Controller {
  public static void index() {
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    stations.sort(Comparator.comparing(Station::getName));

    Thermometer thermometer = new Thermometer();
    Anemometer anemometer = new Anemometer();
    Compass compass = new Compass();

    Logger.info("Rendering Dashboard");
    render("dashboard.html", stations, thermometer, anemometer, compass);
  }

  public static void addStation(String name, float latitude, float longitude) {
    if (name == null || latitude == 0 || longitude == 0) {
      Logger.info("All fields not completed");
      redirect("/dashboard");
    }

    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    Logger.info("Adding a new Station called " + name);
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Logger.info("Deleting a Station");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }
}