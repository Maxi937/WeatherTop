package models;


import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
  public String name;
  public float latitude;
  public float longitude;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<>();


  public Station(String name, float latitude, float longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Reading getLastReading() {
    Reading lastReading = null;

    if (this.readings.size() > 0) {
      lastReading = readings.get(readings.size() - 1);
    }
    return lastReading;
  }

  public Reading getReading(int indexToGet) {
    return this.readings.get(indexToGet);
  }

  public String getName() {
    return this.name;
  }

  ///////
  /* Param String: What type of reading to MinMax -
   *   - "Temp"
   *   - "WindSpeed"
   *   - "WindPressure"
   *  Calls a HashMap of all reading types from each of the readings in The Station
   *  Uses the Param to look up key
   *  Result as HashMap - Min : Max
   *////
  public HashMap<String, Float> getMinMax(String readingToMinMax) {
    HashMap<String, String> readingAsHashMap;
    readingAsHashMap = readings.get(0).getReadingAsHashMap();

    HashMap<String, Float> minMax = new HashMap<>();

    float max = 0;
    float min = Float.parseFloat(readingAsHashMap.get(readingToMinMax));

    for (Reading reading : this.readings) {
      readingAsHashMap = reading.getReadingAsHashMap();

      if (max <= Float.parseFloat(readingAsHashMap.get(readingToMinMax))) {
        max = Float.parseFloat(readingAsHashMap.get(readingToMinMax));
      }
      if (min >= Float.parseFloat(readingAsHashMap.get(readingToMinMax))) {
        min = Float.parseFloat(readingAsHashMap.get(readingToMinMax));
      }
    }
    minMax.put("min", min);
    minMax.put("max", max);

    return minMax;
  }

  public float getMin(String readingToMin) {
    HashMap<String, String> readingAsHashMap;
    readingAsHashMap = readings.get(0).getReadingAsHashMap();

    float minTemp = Float.parseFloat(readingAsHashMap.get(readingToMin));

    for (int i = this.readings.size() - 1; i >= 0; i--) {
      readingAsHashMap = readings.get(i).getReadingAsHashMap();

      if (Float.parseFloat(readingAsHashMap.get(readingToMin)) <= minTemp) {
        minTemp = Float.parseFloat(readingAsHashMap.get(readingToMin));
      }
    }
    return minTemp;
  }

  public float getMax() {
    HashMap<String, String> readingAsHashMap;
    float maxTemp = 0;

    for (Reading reading : this.readings) {
      readingAsHashMap = reading.getReadingAsHashMap();

      if (maxTemp <= Float.parseFloat(readingAsHashMap.get("Temp"))) {
        maxTemp = Float.parseFloat(readingAsHashMap.get("Temp"));
      }
    }
    return maxTemp;
  }

  public String getTrendAnalysis(String readingToTrendAnalysis) {
    ArrayList<Float> lastThreeReadings = new ArrayList<>();
    int x = 0;

    if (this.readings.size() >= 3) {
      for (int i = this.readings.size() - 3; i <= this.readings.size() - 1; i++) {
        HashMap<String, String> readingAsHashMap = readings.get(i).getReadingAsHashMap();
        lastThreeReadings.add(x, Float.valueOf(readingAsHashMap.get(readingToTrendAnalysis)));
        x++;
      }

      if (lastThreeReadings.get(1) > lastThreeReadings.get(0) && lastThreeReadings.get(1) < lastThreeReadings.get(2)) {
        return "arrow up icon";
      }

      if (lastThreeReadings.get(1) < lastThreeReadings.get(0) && lastThreeReadings.get(1) > lastThreeReadings.get(2)) {
        return "arrow down icon";
      }
    }
    return null;
  }
}