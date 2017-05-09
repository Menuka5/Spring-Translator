package hsenid.services;

import hsenid.interfaces.IConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectorRestTemplate implements IConnector {

    private static final Logger logger = LogManager.getLogger(ConnectorRestTemplate.class);

    static String test;
    static JSONObject sendLanguageList;
    static String sendTranslated;
    RestTemplate restTemplate = new RestTemplate();
    JSONParser parser = new JSONParser();

    String UrlForGetAllLanguages = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20160607T111835Z.f64d4276fb712ae3.ed0f150b6046b34df73301472d5e576d32fe3c8b";

    @Autowired
    ModifiedUrlGenerator modifiedUrlGenerator;


    public JSONObject getAllLanguagesList() {

        String getAllLanguagesList = restTemplate.getForObject(UrlForGetAllLanguages, String.class);

        try {

            JSONObject json = (JSONObject) parser.parse(getAllLanguagesList);
            sendLanguageList = (JSONObject) parser.parse(json.get("langs").toString());

        } catch (ParseException e) {
            logger.error(e.getMessage());
        }


        return sendLanguageList;

    }

    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage){

        sendTranslated = restTemplate.getForObject(modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage), String.class);

        logger.info(sendTranslated.toString());
        return sendTranslated;
    }
}
