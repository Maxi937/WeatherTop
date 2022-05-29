package weatherinstrumentpckg;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class WeatherInstrument {

  protected Map<String, Object> weatherInstrumentData;

  public WeatherInstrument() {
    weatherInstrumentData = loadData("weatherInstrumentData.yml");
  }

  private Map<String, Object> loadData(String instrumentDataFileName) {
    Yaml yaml = new Yaml();
    InputStream inputStream = WeatherInstrument.class
        .getClassLoader()
        .getResourceAsStream(instrumentDataFileName);
    weatherInstrumentData = (Map<String, Object>) yaml.load(inputStream);
    //Logger.info ("Instrument Data Loaded Successfully ");
    return weatherInstrumentData;
  }

}
