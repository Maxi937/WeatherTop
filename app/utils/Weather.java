package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Weather {
    private ArrayList<String> weather;

    public Weather(ArrayList<String> weather){
        this.weather=weather;
    }

    public ArrayList<String> getWeatherArray(){
        return weather;
    }


}


