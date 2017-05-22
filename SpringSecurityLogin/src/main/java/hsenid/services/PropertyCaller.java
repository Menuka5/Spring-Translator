package hsenid.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyCaller {
    public String sendProperty(String key){

        Properties prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        String sendRequestedData = null;

        try (InputStream input = classLoader.getResourceAsStream("config.properties")){
            prop.load(input);
            sendRequestedData = prop.getProperty(key);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return sendRequestedData;
    }

}
