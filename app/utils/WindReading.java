package utils;


import java.util.HashMap;

public class WindReading {
    private final HashMap<String, String> weatherReading;
    private final double windDirectionMin;
    private final double windDirectionMax;
    private final String windDirection;

    WindReading(HashMap<String, String> weatherReading){
        this.weatherReading = weatherReading;
        this.windDirectionMin = Double.parseDouble(weatherReading.get("min"));
        this.windDirectionMax = Double.parseDouble(weatherReading.get("max"));
        this.windDirection = weatherReading.get("direction");
    }

    public void getWeatherReadingClass(){
        System.out.println(weatherReading.getClass());
    }

    public void getWeatherReadingKeys(){
        for (String key : weatherReading.keySet()){
            System.out.println(key);
        }
    }

    public void getWeatherReadingValues(){
        System.out.println(this.windDirection);
        System.out.println(this.windDirectionMin);
        System.out.println(this.windDirectionMax);
    }

    public Boolean checkWindDirection(double windDirection) {
        return windDirection >= this.windDirectionMin && windDirection <= this.windDirectionMax;
    }

    public String getWindDirection(){
        return this.windDirection;
    }

    public double getWindDirectionMin(){
        return this.windDirectionMin;
    }

    public double getWindDirectionMax(){
        return this.windDirectionMax;
    }

}
