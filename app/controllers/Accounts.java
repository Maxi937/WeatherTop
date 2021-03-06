package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import weatherinstrumentpckg.Anemometer;

import java.util.Comparator;
import java.util.List;

public class Accounts extends Controller {
  public static void index() {
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    stations.sort(Comparator.comparing(Station::getName));
    Logger.info("rendering member.html");
    Logger.info("Member ID: " + member);
    Anemometer anemometer = new Anemometer();
    render("member.html", member, anemometer, stations);
  }

  public static void signup() {
    render("signup.html");
  }

  public static void login() {
    render("login.html");
  }

  public static void register(Long id, String firstname, String lastname, String email, String password) {
    Logger.info("Registering new user " + email);
    Member member = new Member(firstname, lastname, email, password);
    member.save();
    redirect("/");
  }

  public static void authenticate(String email, String password) {

    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      System.out.println(member);
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout() {
    session.clear();
    redirect("/");
  }

  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }

}
