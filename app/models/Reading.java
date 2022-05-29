package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Entity
public class Reading extends Model {
  public int code;
  public float temperature;
  public float windSpeed;
  public int windPressure;
  public float windDirection;
  public Date date;


  public Reading(Date date, int code, float temperature, float windSpeed, int windPressure, float windDirection) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windPressure = windPressure;
    this.windDirection = windDirection;
    this.date = date;
  }

  public String getDate() {
    return String.format("%tF %<tT", date);
  }

  public HashMap<String, String> getReadingAsHashMap() {
    HashMap<String, String> readingAsHashMap = new HashMap<>();
    readingAsHashMap.put("code", String.valueOf(this.code));
    readingAsHashMap.put("windSpeed", String.valueOf(this.windSpeed));
    readingAsHashMap.put("windDirection", String.valueOf(this.windDirection));
    readingAsHashMap.put("windPressure", String.valueOf(this.windPressure));
    readingAsHashMap.put("Temp", String.valueOf(this.temperature));
    return readingAsHashMap;
  }

  public ArrayList<String> getReadingAsList() {
    ArrayList<String> readingAsList = new ArrayList<>();
    readingAsList.add(String.valueOf(this.code));
    readingAsList.add(String.valueOf(this.windSpeed));
    readingAsList.add(String.valueOf(this.windDirection));
    readingAsList.add(String.valueOf(this.windPressure));
    readingAsList.add(String.valueOf(this.temperature));
    return readingAsList;
  }
}

