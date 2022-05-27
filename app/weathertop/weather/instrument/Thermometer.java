package weathertop.weather.instrument;

import models.Reading;

public class Thermometer extends WeatherInstrument {

  public Thermometer() {
  }


  public float getCelsiusToFahrenheit(Reading reading) {
    return reading.temperature * 9 / 5 + 32;
  }

  public double getWindChill(Reading reading) {
    float temperatureAsCelsius = reading.temperature;
    double v = Math.pow(reading.windSpeed, 0.16);

    return 13.12 + (.6215 * temperatureAsCelsius) - (11.37 * v) + (.3965 * temperatureAsCelsius * v);
  }


}
