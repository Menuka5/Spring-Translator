package hsenid.services.apicallers;

import hsenid.interfaces.IConnector;
import hsenid.models.JsonStore;
import hsenid.services.ModifiedUrlGenerator;
import hsenid.services.PropertyCaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

public class ConnectorRestTemplate implements IConnector {

    private static final Logger logger = LogManager.getLogger(ConnectorRestTemplate.class);

    ModifiedUrlGenerator modifiedUrlGenerator = new ModifiedUrlGenerator();
    JsonStore jsonStore = new JsonStore();
    PropertyCaller propertyCaller = new PropertyCaller();

    RestTemplate restTemplate = new RestTemplate();
    JSONParser parser = new JSONParser();
    String UrlForGetAllLanguages = propertyCaller.sendProperty("alllanguageurl");



    public JSONObject getAllLanguagesList() {

        String getAllLanguagesList = restTemplate.getForObject(UrlForGetAllLanguages, String.class);

        try {

            jsonStore.setJsonObject((JSONObject) parser.parse(getAllLanguagesList));

        } catch (ParseException e) {
            logger.error(e.getMessage());
        }

        return (JSONObject) jsonStore.getJsonObject().get("langs");

    }

    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage){

        jsonStore.setString(restTemplate.getForObject(modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage), String.class));

        return jsonStore.getString();
    }
}
