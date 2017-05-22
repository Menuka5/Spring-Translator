package hsenid.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyCaller {
    private static final Logger logger = LogManager.getLogger(PropertyCaller.class);
    public String sendProperty(String key){

        Properties prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        String sendRequestedData = null;

        try (InputStream input = classLoader.getResourceAsStream("config.properties")){
            prop.load(input);
            sendRequestedData = prop.getProperty(key);
        } catch (NullPointerException | IOException  e) {
            logger.error(e);
        }

        return sendRequestedData;
    }

}
