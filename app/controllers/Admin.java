package controllers;

import java.util.List;

import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class Admin extends Controller {
  public static void index() {
    Logger.info("Rendering Admin");

    List<Reading> reading = Reading.findAll();
    render("Admin.html", reading);
  }
}