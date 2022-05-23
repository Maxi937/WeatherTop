package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String name;
    public float latitude;
    public float longitude;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();


    public Station(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Reading getLastReading(){
        Reading lastReading = null;

        if (this.readings.size()>0) {
            lastReading = readings.get(readings.size()-1);
        }

        return lastReading;
    }

    public Reading getReading(int indexToGet){
        return this.readings.get(indexToGet);
    }


    /* Param String: What type of reading to MinMax -
        - "Temp"
        - "WindSpeed"
        - "WindPressure"
       Calls a HashMap of all reading types from each of the readings in The Station
       Uses the Param to look up key
       MinMax
       Result as HashMap - Min : Max
     */
    public HashMap<String, Float> getMinMax(String readingToMinMax){
        HashMap<String, String> readingAsHashMap;
        HashMap<String, Float> result = new HashMap<>();

        float max = 0;
        float min;

        for (int i = 0; i < this.readings.size(); i++){
            readingAsHashMap = readings.get(i).getReadingAsHashMap();

            if (max <= Float.parseFloat(readingAsHashMap.get(readingToMinMax))){
                max = Float.parseFloat(readingAsHashMap.get(readingToMinMax));
            }
            System.out.println(readingAsHashMap);
        }
        System.out.println("Max "+ readingToMinMax +": " + max);

        min = getMin(max, readingToMinMax);

        result.put("min", min);
        result.put("max", max);

        return result;
    }

    // Min by starting at the Max value and decrementing through each temp, updating new Min if < current Min
    public float getMin(float max, String readingToMin){
        HashMap<String, String> readingAsHashMap;
        float minTemp = max;

        for (int i = this.readings.size()-1; i >= 0; i--){
            readingAsHashMap = readings.get(i).getReadingAsHashMap();

            if (Float.parseFloat(readingAsHashMap.get(readingToMin)) <= minTemp){
                minTemp = Float.parseFloat(readingAsHashMap.get(readingToMin));
            }
            System.out.println(readingAsHashMap);
        }
        System.out.println("Min "+ readingToMin + ": " + minTemp);
        return minTemp;
    }

    public float getMax(){
        HashMap<String, String> readingAsHashMap;
        float maxTemp = 0;
        float minTemp;

        for (int i = 0; i < this.readings.size(); i++){
            readingAsHashMap = readings.get(i).getReadingAsHashMap();

            if (maxTemp <= Float.parseFloat(readingAsHashMap.get("Temp"))){
                maxTemp = Float.parseFloat(readingAsHashMap.get("Temp"));
            }
            System.out.println(readingAsHashMap);
        }
        System.out.println("Max Temperature: " + maxTemp);
        return maxTemp;
    }


}