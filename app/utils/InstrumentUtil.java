package utils;

import org.yaml.snakeyaml.Yaml;
import play.Logger;

import java.io.InputStream;
import java.util.Map;

public class InstrumentUtil {
    public static Map<String, Object> instrumentData;

    public static void loadData(String instrumentDataFileName){
        Yaml yaml = new Yaml();
        InputStream inputStream = InstrumentUtil.class
                .getClassLoader()
                .getResourceAsStream(instrumentDataFileName);
        instrumentData = (Map<String, Object>) yaml.load(inputStream);
        Logger.info ("Instrument Data Loaded Successfully ");
    }

    public static Map<String, Object> getData(){
        return instrumentData;
    }
}
