package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import weathertop.weather.instrument.Anemometer;
import weathertop.weather.instrument.Compass;
import weathertop.weather.instrument.Thermometer;

public class MemberCtrl extends Controller {

  public static void index() {
    Member member = Accounts.getLoggedInMember();
    Logger.info("rendering editmemberdetails.html");
    Logger.info("Member ID: " + member);
    render("editmemberdetails.html", member);
  }


  public static void editDetails(Member member, String firstName, String lastName, String email) {
    member = Accounts.getLoggedInMember();

    Logger.info(member.firstName);
    Logger.info(lastName);
    Logger.info(email);

    if (firstName.equals("") || lastName.equals("") || email.equals("")) {
      if (firstName.equals("")) {
        firstName = member.firstName;
      }
      if (lastName.equals("")) {
        lastName = member.lastName;
      }
      if (email.equals("")) {
        email = member.email;
      }
      Logger.info("All fields not completed");
    }

    member.firstName = firstName;
    member.lastName = lastName;
    member.email = email;
    member.save();
    Logger.info("Editing Member: " + member);
    redirect("/member");
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
